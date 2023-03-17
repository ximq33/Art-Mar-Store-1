package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.domain.products.Variant;
import com.ArtMar_Store.Api.domain.products.VariantId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VariantRepository extends MongoRepository<Variant, VariantId> {
}
