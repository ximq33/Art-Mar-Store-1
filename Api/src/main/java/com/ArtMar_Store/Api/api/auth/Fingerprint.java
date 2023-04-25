package com.ArtMar_Store.Api.api.auth;

import com.ArtMar_Store.Api.domain.users.UserId;
import org.springframework.data.annotation.Id;

public record Fingerprint(
        String value,
        @Id
        UserId userId
) {
}
