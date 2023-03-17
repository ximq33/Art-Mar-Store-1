package com.ArtMar_Store.Api.domain.users;

public record UserId (String value) {
    static UserId newId(String value) {
        return new UserId(value);
    }
}
