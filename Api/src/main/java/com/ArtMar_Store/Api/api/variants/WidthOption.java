package com.ArtMar_Store.Api.api.variants;

import java.math.BigDecimal;

public record WidthOption(
        Integer width,
        Integer quantity,
        BigDecimal price
) {
}
