package com.ArtMar_Store.Api.api.auth;

import com.ArtMar_Store.Api.api.auth.Token;
import com.ArtMar_Store.Api.domain.users.AppUser;
import jakarta.servlet.http.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
class TokenController {


    private final FingerprintService fingerprintService;

    public TokenController(FingerprintService fingerprintService, JwtEncoder encoder) {
        this.fingerprintService = fingerprintService;
        this.encoder = encoder;
    }

    JwtEncoder encoder;

    @PostMapping("/token")
    public ResponseEntity<Token> token(Authentication authentication) throws NoSuchAlgorithmException {

        AppUser appUser = (AppUser) authentication.getPrincipal();
        Fingerprint fingerprint =  fingerprintService.getFingerprint(appUser.userId());
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(fingerprint.value().getBytes());
        String stringHash = Arrays.toString(messageDigest.digest());


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
                .claim("fingerprint", stringHash)
                .build();
        Token token = Token.newOf(this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
        Cookie cookie = new Cookie("userFingerprint", fingerprint.value());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1800);
        //cookie.setSecure(true);      before deploy to the same domain uncomment
        String cookieString = String.format("%s=%s; HttpOnly", cookie.getName(), cookie.getValue());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookieString).body(token);
    }
}
