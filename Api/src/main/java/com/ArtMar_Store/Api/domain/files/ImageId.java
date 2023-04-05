package com.ArtMar_Store.Api.domain.files;

public record ImageId(String value) {
    ImageId newId(String value){
        return new ImageId(value);
    }
}
