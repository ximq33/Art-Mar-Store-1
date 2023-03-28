package com.ArtMar_Store.Api.api.users;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token/test")
public class TokenTestController {

    @GetMapping("/")
    public String hello(Authentication authentication) {
        return "hello, " + authentication.getName() + "\n Authorities: "
                + authentication.getAuthorities().toString();
    }
}
