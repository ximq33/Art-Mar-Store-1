package com.ArtMar_Store.Api.domain.variants;

import com.ArtMar_Store.Api.domain.products.ProductId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

@Document
public record Variant(
        @MongoId VariantId variantId,
        BigDecimal price,
        Integer quantity,
        Boolean disabled,
        String imgPath,
        String manufacturer,
        Color color,
        String side,
        String pattern,
        ProductId productId) {
}
