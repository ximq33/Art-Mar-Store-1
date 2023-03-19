package com.ArtMar_Store.Api.domain.variants;

import com.ArtMar_Store.Api.domain.products.ProductId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public record Variant(@Id VariantId variantId,
                      BigDecimal price,
                      int quantity,
                      boolean disabled,
                      String imgPath,
                      String manufacturer,
                      Color color,
                      String side,
                      String pattern,
                      ProductId productId) {
}
