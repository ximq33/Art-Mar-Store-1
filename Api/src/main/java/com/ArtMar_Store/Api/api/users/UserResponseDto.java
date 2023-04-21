package com.ArtMar_Store.Api.api.users;

import com.ArtMar_Store.Api.domain.users.AppUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public record UserResponseDto(
    String userId,
    String userName,
    List<? extends GrantedAuthority> authorities
) {
    public static UserResponseDto fromDomain(AppUser user) {
        return new UserResponseDto(
                user.userId().value(),
                user.getUsername(),
                user.getAuthorities().stream().toList()
        );
    }
}
