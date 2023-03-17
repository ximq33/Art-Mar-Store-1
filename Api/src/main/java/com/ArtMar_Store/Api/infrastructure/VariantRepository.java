package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.domain.products.Color;
import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.products.Variant;
import com.ArtMar_Store.Api.domain.products.VariantId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface VariantRepository extends MongoRepository<Variant, VariantId> {

    boolean existsByManufacturerAndColorAndSideAndPatternAndProductId(String manufacturer, Color color, String side, String pattern, ProductId productId);
}
