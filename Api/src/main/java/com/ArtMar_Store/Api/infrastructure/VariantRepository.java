package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.domain.products.Product;
import com.ArtMar_Store.Api.domain.variants.Color;
import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.variants.Variant;
import com.ArtMar_Store.Api.domain.variants.VariantId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface VariantRepository extends MongoRepository<Variant, VariantId> {

    boolean existsByManufacturerAndColorAndSideAndPatternAndProductId(String manufacturer, Color color, String side, String pattern, ProductId productId);

    List<Variant> findByProductId(ProductId productId);
}
