package com.ArtMar_Store.Api.domain.products;

public record VariantId(String value) {
    static VariantId newId(String value){
        return new VariantId(value);
    }
}
