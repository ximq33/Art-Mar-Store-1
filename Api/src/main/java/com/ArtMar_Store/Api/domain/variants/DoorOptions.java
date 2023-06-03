package com.ArtMar_Store.Api.domain.variants;

import com.ArtMar_Store.Api.api.variants.WidthOption;

import java.util.List;

public record DoorOptions(
        List<WidthOption> left,
        List<WidthOption> right
) implements VariantOptions {
}
