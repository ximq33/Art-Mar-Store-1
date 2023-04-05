package com.ArtMar_Store.Api.domain.files;

import org.bson.types.Binary;

public record Image(ImageId imageId, Binary imageBson, String productId, String extension) {
}
