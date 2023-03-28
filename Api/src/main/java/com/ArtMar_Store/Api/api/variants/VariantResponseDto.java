package com.ArtMar_Store.Api.api.variants;

import com.ArtMar_Store.Api.domain.products.*;
import com.ArtMar_Store.Api.domain.variants.Color;
import com.ArtMar_Store.Api.domain.variants.Variant;

import java.math.BigDecimal;

record VariantResponseDto(String variantId,
                          BigDecimal price,
                          Integer quantity,
                          boolean disabled,
                          String imgPath,
                          String manufacturer,
                          String colorName,
                          String RGBvalue,
                          String side,
                          String pattern,
                          ProductId productId
) {

    public static VariantResponseDto fromDomain(Variant variant) {
        return new VariantResponseDto(variant.variantId().value(), variant.price(),
                variant.quantity(), variant.disabled(), variant.imgPath(), variant.manufacturer(),
                variant.color().colorName(), variant.color().RGBvalue(), variant.side(), variant.pattern(), variant.productId());

    }
}
