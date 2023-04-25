package com.ArtMar_Store.Api.api.auth;

import com.ArtMar_Store.Api.domain.users.UserId;

public record Fingerprint(
        String value,
        UserId userId
) {
}
