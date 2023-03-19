package com.ArtMar_Store.Api.domain.products;
import com.ArtMar_Store.Api.domain.variants.Variant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document
public record Product(

        @Id
        ProductId productId,
        String name,

        String manufacturer,

        List<Variant> variants,

        BigDecimal price,

        String imgPath,

        String description) {
}
