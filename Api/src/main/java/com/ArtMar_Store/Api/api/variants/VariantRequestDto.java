package com.ArtMar_Store.Api.api.variants;

import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.variants.Color;
import com.ArtMar_Store.Api.domain.variants.DoorOptions;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record VariantRequestDto(
        @NotNull(message = "variant name cannot be null")
        String variantName,
        @NotNull(message = "price cannot be null")
        @Positive(message = "price has to be positive")
        BigDecimal price,
        @NotNull(message = "quantity cannot be null")
        @PositiveOrZero(message = "quantity cannot be negative")
        Integer quantity,
        Boolean enabled,
        @NotNull(message = "manufacturer cannot be null")
        @NotEmpty(message = "manufacturer cannot be empty")
        String manufacturer,
        @NotNull(message = "color cannot be null")
        Color color,
        @NotNull(message = "variant options cannot be null")
        DoorOptions doorOptions,
        @NotNull(message = "product ID cannot be null")
        ProductId productId
) {
}
