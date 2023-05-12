package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.api.products.ErrorDTO;
import com.ArtMar_Store.Api.domain.users.AppUserService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
class SecurityConfig {

    private final AppUserService userService;
    private final CookieAuthenticationFilter cookieAuthenticationFilter;

    public SecurityConfig(AppUserService userService, CookieAuthenticationFilter cookieAuthenticationFilter) {
        this.userService = userService;
        this.cookieAuthenticationFilter = cookieAuthenticationFilter;
    }



    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return userService.getEncoder();
    }

    @Value("${jwt.public.key}")
    RSAPublicKey key;

    @Value("${jwt.private.key}")
    RSAPrivateKey priv;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .cors()
                .and()
                .csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/token", HttpMethod.POST.name()),
                        new AntPathRequestMatcher("/users", HttpMethod.POST.name()),
                        new AntPathRequestMatcher("/products", HttpMethod.GET.name()),
                        new AntPathRequestMatcher("/variants", HttpMethod.GET.name())
                        ))

                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.DELETE).hasAnyAuthority("SCOPE_ADMIN")

                        .requestMatchers(HttpMethod.POST, "/token").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products/**").hasAnyAuthority("SCOPE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/variants/**").hasAnyAuthority("SCOPE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/variants/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users").hasAnyAuthority("SCOPE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/fromToken").hasAnyAuthority("SCOPE_ADMIN", "SCOPE_USER")
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("SCOPE_ADMIN")

                        .requestMatchers(HttpMethod.GET, "/files/**").permitAll()

                        .anyRequest()
                        .authenticated()
                )
                .addFilterAfter(cookieAuthenticationFilter, BasicAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()));
        return http.build();
    }

    @Bean
    UserDetailsService users() {
        return userService::findByUserNameOrEmail;
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(users())
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.key).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
