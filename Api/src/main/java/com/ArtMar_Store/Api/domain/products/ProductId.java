package com.ArtMar_Store.Api.domain.products;

public record ProductId(String value) {
    ProductId newId(String value){
        return new ProductId(value);
    }
}
