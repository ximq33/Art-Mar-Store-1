package com.ArtMar_Store.Api.api.products;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import java.math.BigDecimal;

public record ProductRequestDto(
        @NotNull(message = "name cannot be null")
        @NotEmpty(message = "name cannot be empty")
        @Length(max = 200, message = "name cannot be longer than 200")
        String name,

        @NotNull
        @NotEmpty
        String manufacturer,

        @NotNull(message = "price cannot be null")
        @Positive(message = "price must be positive")
        BigDecimal price,


        @NotNull
        String description
) {
}
