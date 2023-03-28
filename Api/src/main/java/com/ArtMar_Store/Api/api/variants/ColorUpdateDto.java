package com.ArtMar_Store.Api.api.variants;

import org.hibernate.validator.constraints.Length;

import java.util.Optional;

public record ColorUpdateDto(@Length(min = 1, max = 100)
                             Optional<String> colorName,
                             @Length(min = 1, max = 100)
                             Optional<String> RGB
) {
}
