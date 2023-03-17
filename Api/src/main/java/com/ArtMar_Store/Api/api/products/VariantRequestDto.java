package com.ArtMar_Store.Api.api.products;

import com.ArtMar_Store.Api.domain.products.Color;
import com.ArtMar_Store.Api.domain.products.ProductId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


import java.math.BigDecimal;

public record VariantRequestDto(

        @NotNull(message = "price cannot be null")
        @Positive(message = "price has to be positive")
        BigDecimal price,
        @NotNull(message = "quantity cannot be null")
        @PositiveOrZero(message = "quantity cannot be negative")
        int quantity,
        @NotNull(message = "image path cannot be null")
        String imgPath,
        @NotNull(message = "manufacturer cannot be null")
        @NotEmpty(message = "manufacturer cannot be empty")
        String manufacturer,
        @NotNull(message = "color cannot be null")
        Color color,
        @NotNull(message = "side cannot be null")
        String side,
        @NotNull(message = "pattern cannot be null")
        String pattern,
        @NotNull(message = "product ID cannot be null")
        ProductId productId
) {
}
