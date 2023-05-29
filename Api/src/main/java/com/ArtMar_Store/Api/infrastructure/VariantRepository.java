package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.variants.Variant;
import com.ArtMar_Store.Api.domain.variants.VariantId;
import com.ArtMar_Store.Api.domain.variants.VariantOptions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface VariantRepository extends MongoRepository<Variant, VariantId> {

    boolean existsByManufacturerAndVariantOptionsAndProductId(String manufacturer, VariantOptions options, ProductId productId);

    List<Variant> findByProductId(ProductId productId);
}
