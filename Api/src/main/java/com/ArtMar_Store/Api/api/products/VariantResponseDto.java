package com.ArtMar_Store.Api.api.products;

import com.ArtMar_Store.Api.domain.products.*;

import java.math.BigDecimal;

record VariantResponseDto(String variantId,
                          BigDecimal price,
                          int quantity,
                          boolean disabled,
                          String imgPath,
                          String manufacturer,
                          Color color,
                          String side,
                          String pattern,
                          ProductId productId
) {

    public static VariantResponseDto fromDomain(Variant variant) {
        return new VariantResponseDto(variant.variantId().value(), variant.price(),
                variant.quantity(), variant.disabled(), variant.imgPath(), variant.manufacturer(),
                variant.color(), variant.side(), variant.pattern(), variant.productId());

    }
}
