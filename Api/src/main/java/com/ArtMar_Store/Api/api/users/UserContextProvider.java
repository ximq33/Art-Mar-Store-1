package com.ArtMar_Store.Api.api.users;

import com.ArtMar_Store.Api.domain.users.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;

public enum UserContextProvider {
    INSTANCE;

    public static AppUser getUserContext() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
