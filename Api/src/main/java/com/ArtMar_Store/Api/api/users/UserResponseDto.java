package com.ArtMar_Store.Api.api.users;

import com.ArtMar_Store.Api.domain.users.AppUser;

public record UserResponseDto(
    String userId,
    String userName
) {
    public static UserResponseDto fromDomain(AppUser user) {
        return new UserResponseDto(
                user.userId().value(),
                user.getUsername());
    }
}
