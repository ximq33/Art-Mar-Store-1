package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.domain.files.Image;
import com.ArtMar_Store.Api.domain.files.ImageId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FileStorageRepository extends MongoRepository<Image, ImageId> {

    Image getImagesByProductId(String productId);
}
