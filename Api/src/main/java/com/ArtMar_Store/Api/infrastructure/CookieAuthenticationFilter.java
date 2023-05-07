package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.api.auth.Fingerprint;
import com.ArtMar_Store.Api.api.auth.FingerprintService;
import com.ArtMar_Store.Api.api.products.ErrorDTO;
import com.ArtMar_Store.Api.domain.users.UnableToRegisterException;
import com.ArtMar_Store.Api.domain.users.UserId;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class CookieAuthenticationFilter extends OncePerRequestFilter {

    private final FingerprintService fingerprintService;

    public CookieAuthenticationFilter(FingerprintService fingerprintService) {
        this.fingerprintService = fingerprintService;
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, CookieException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String userIdString;
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            Map<String, Object> claims = jwt.getClaims();
            userIdString = ((Map<String, String>) claims.get("userId")).get("value");
        } catch (ClassCastException e) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userFingerprint")) {
                    String cookieValue = cookie.getValue();
                    if (cookieValue != null && !cookieValue.isBlank()) {
                        UserId userId = new UserId(userIdString);
                        String repositoryFingerprint = fingerprintService.getFingerprint(userId).value();
                        if (!cookieValue.equals(repositoryFingerprint)) {
                            // The fingerprint in the cookie does not match the one in the repository
                            // Set HTTP status code 401 (Unauthorized) and return immediately
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            System.out.println("Cookie doesn't match");
                            return;
                        }

                        //Generate new fingerprint and set it as a response cookie
                        fingerprintService.newFingerprint(userId);
                        Fingerprint fingerprint = fingerprintService.getFingerprint(userId);
                        Cookie newCookie = new Cookie("userFingerprint", fingerprint.value());
                        newCookie.setHttpOnly(true);
                        newCookie.setMaxAge(1800);

                        response.addCookie(newCookie);
                    }
                    break;
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println("No cookie found");
            return;
        }
        filterChain.doFilter(request, response);
    }

}
