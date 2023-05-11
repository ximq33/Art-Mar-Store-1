package com.ArtMar_Store.Api.api.variants;

import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.variants.Variant;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

record VariantResponseDto(String variantId,
                          BigDecimal price,
                          Integer quantity,
                          boolean enabled,
                          String manufacturer,
                          String colorName,
                          String RGBvalue,
                          String side,
                          String pattern,
                          ProductId productId,
                          String productName,
                          String addedDate
) {

    public static VariantResponseDto fromDomain(Variant variant) {
        return new VariantResponseDto(variant.variantId().value(), variant.price(), variant.quantity(),
                variant.enabled(), variant.manufacturer(), variant.color().colorName(), variant.color().RGBvalue(),
                variant.side(), variant.pattern(), variant.productId(), variant.productName(), variant.addedDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
