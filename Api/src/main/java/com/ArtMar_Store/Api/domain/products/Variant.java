package com.ArtMar_Store.Api.domain.products;

import java.math.BigDecimal;

public record Variant(VariantId variantId,
                      String name,
                      BigDecimal price,
                      int quantity,
                      boolean disabled,
                      String imgName,
                      String manufacturer,
                      String description,
                      Color color,
                      String side,
                      String pattern,
                      Product product) {
}
