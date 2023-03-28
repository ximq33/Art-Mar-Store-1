package com.ArtMar_Store.Api.api.users;

import com.ArtMar_Store.Api.domain.users.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
class TokenController {

    private final AppUserService service;

    public TokenController(AppUserService service) {
        this.service = service;
    }

    @Autowired
    JwtEncoder encoder;

    @PostMapping("/token")
    public String token(Authentication authentication) {


        Instant now = Instant.now();
        long expiry = 1800L;
        // @formatter:off
        Collection<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", authorities)
                .build();
        // @formatter:on
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
