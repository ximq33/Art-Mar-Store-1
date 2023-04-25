package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.api.auth.FingerprintService;
import com.ArtMar_Store.Api.domain.users.AppUser;
import com.ArtMar_Store.Api.domain.users.UserId;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
public class CookieAuthenticationFilter extends OncePerRequestFilter{

    private final FingerprintService fingerprintService;

    public CookieAuthenticationFilter(FingerprintService fingerprintService) {
        this.fingerprintService = fingerprintService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId;
        boolean isBasic = false;
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            Map<String, Object> claims = jwt.getClaims();
            userId = ((Map<String, String>) claims.get("userId")).get("value");
        }
        catch (ClassCastException e) {
            AppUser appUser = (AppUser) authentication.getPrincipal();
            userId = appUser.userId().value();
            isBasic = true;
        }
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userFingerprint")) {
                    String cookieValue = cookie.getValue();
                    if (cookieValue != null && !cookieValue.isBlank()) {
                        String repositoryFingerprint = fingerprintService.getFingerprint(new UserId(userId)).value();
                        if (!cookieValue.equals(repositoryFingerprint)) {
                            // The fingerprint in the cookie does not match the one in the repository
                            // Set HTTP status code 401 (Unauthorized) and return immediately
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            return;
                        }
                    }
                    break;
                }
            }
        } else if (!isBasic) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request, response);
    }

}
