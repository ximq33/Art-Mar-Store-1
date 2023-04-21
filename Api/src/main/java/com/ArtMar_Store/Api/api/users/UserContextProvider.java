package com.ArtMar_Store.Api.api.users;

import com.ArtMar_Store.Api.domain.users.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public enum UserContextProvider {
    INSTANCE;

    public static AppUser getUserContext() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
