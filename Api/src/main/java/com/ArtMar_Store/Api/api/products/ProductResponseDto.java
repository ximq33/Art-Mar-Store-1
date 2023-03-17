package com.ArtMar_Store.Api.api.products;

import com.ArtMar_Store.Api.domain.products.Product;

import java.math.BigDecimal;

public record ProductResponseDto(
        String productId,
        String name,
        String manufacturer,
        BigDecimal price,
        String description) {

    static ProductResponseDto fromDomain(Product product) {
        return new ProductResponseDto(
                product.productId().value(),
                product.name(),
                product.manufacturer(),
                product.price(),
                product.description());
    }
}
