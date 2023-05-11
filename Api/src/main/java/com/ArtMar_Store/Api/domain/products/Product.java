package com.ArtMar_Store.Api.domain.products;
import com.ArtMar_Store.Api.domain.files.ImageId;
import com.ArtMar_Store.Api.domain.variants.Variant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;

@Document
public record Product(

        @MongoId
        ProductId productId,
        String name,
        String manufacturer,
        BigDecimal price,
        String description,
        String variantImageId) {


}
