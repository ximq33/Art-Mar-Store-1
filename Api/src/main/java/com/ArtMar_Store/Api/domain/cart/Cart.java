package com.ArtMar_Store.Api.domain.cart;

import com.ArtMar_Store.Api.domain.variants.Variant;

import java.util.List;

public record Cart(
        List<Variant> variants        //TODO Check if deleted variant from repository also deletes itself from cart

) {
}
