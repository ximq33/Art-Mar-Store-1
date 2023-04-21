package com.ArtMar_Store.Api.api.users;

import com.ArtMar_Store.Api.domain.users.AppUser;
import com.ArtMar_Store.Api.domain.users.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
class TokenController {


    public TokenController(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    JwtEncoder encoder;

    @PostMapping("/token")
    public ResponseEntity<Token> token(Authentication authentication) {

        AppUser appUser = (AppUser) authentication.getPrincipal();


        Instant now = Instant.now();
        long expiry = 1800L;
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
                .claim("userId", appUser.userId())
                .build();
        return ResponseEntity.ok(Token.newOf(this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue()));
    }
}
