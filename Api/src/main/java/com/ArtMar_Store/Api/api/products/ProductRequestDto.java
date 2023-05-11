package com.ArtMar_Store.Api.api.products;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ProductRequestDto(
        @NotNull(message = "name cannot be null")
        @NotEmpty(message = "name cannot be empty")
        @Length(max = 200, message = "name cannot be longer than 200")
        String name,
        @NotNull
        @NotEmpty
        String manufacturer,
        @NotNull
        String description
) {
}
