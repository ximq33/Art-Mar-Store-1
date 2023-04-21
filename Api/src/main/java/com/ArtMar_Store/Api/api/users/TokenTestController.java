package com.ArtMar_Store.Api.api.users;

import com.ArtMar_Store.Api.domain.users.AppUserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token/test")
public class TokenTestController {

    public TokenTestController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    private final AppUserService appUserService;

    @GetMapping("/")
    public String hello(Authentication authentication) {
        return "hello, " + authentication.getName() + "\n Authorities: "
                + authentication.getAuthorities().toString();

    }
}
