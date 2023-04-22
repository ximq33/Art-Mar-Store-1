package com.ArtMar_Store.Api.domain.files;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.MongoId;

public record Image(@MongoId ImageId imageId, Binary imageBson, String productId, String extension) {
}
