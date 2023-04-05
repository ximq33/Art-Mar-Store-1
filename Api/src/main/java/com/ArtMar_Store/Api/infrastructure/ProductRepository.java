package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.domain.products.Product;
import com.ArtMar_Store.Api.domain.products.ProductId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, ProductId> {

    boolean existsByNameAndManufacturer(String name, String manufacturer);

    Product findByNameAndManufacturer(String name, String manufacturer);
}
