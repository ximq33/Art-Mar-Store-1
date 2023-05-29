package com.ArtMar_Store.Api.domain.variants;

import java.util.Map;

public record DoorOptions(
        Map<Integer, Integer> left,
        Map<Integer, Integer> right
) implements VariantOptions {
}
