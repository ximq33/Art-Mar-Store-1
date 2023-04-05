package com.ArtMar_Store.Api.domain.files;

public record ImageResponseDto(String imageId, byte[] image, String productId, String extension) {

    static ImageResponseDto fromDomain(Image image){
        return new ImageResponseDto(image.imageId().value(), image.imageBson().getData(), image.productId(), image.extension());
    }

}
