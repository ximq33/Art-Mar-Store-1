package com.ArtMar_Store.Api.domain.products;
import java.math.BigDecimal;
import java.util.List;

public record Product(

        ProductId productId,
        String name,

        String manufacturer,

        List<Variant> variants,

        BigDecimal price,

        String imgPath,

        String description) {
}
