package com.ArtMar_Store.Api.domain.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;

@Document(collection = "users")
public record AppUser(
        @Id
        UserId userId,
        String name,
        String email,
        String password,
        List<String> roles,
        Boolean enabled
)  {}

