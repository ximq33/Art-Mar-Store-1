package com.ArtMar_Store.Api.domain.variants;

import com.ArtMar_Store.Api.domain.products.ProductId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
public record Variant(
        @MongoId
        VariantId variantId,
        String variantName,
        BigDecimal price,
        Integer quantity,
        Boolean enabled,
        String manufacturer,
        Color color,
        VariantOptions variantOptions,
        ProductId productId,
        String productName,
        LocalDateTime addedDate) {
}
