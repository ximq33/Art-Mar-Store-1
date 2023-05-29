package com.ArtMar_Store.Api.api.variants;

import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.variants.Color;
import com.ArtMar_Store.Api.domain.variants.DoorOptions;
import com.ArtMar_Store.Api.domain.variants.Variant;
import com.ArtMar_Store.Api.domain.variants.VariantOptions;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

record VariantResponseDto(String variantId,
                          String variantName,
                          BigDecimal price,
                          Integer quantity,
                          boolean enabled,
                          String manufacturer,
                          Color color,
                          VariantOptions variantOptions,
                          ProductId productId,
                          String productName,
                          String addedDate
) {

    public static VariantResponseDto fromDomain(Variant variant) {
        DoorOptions doorOptions = (DoorOptions) variant.variantOptions();
        return new VariantResponseDto(variant.variantId().value(), variant.variantName(), variant.price(), variant.quantity(),
                variant.enabled(), variant.manufacturer(), variant.color(),
                doorOptions, variant.productId(), variant.productName(), variant.addedDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
