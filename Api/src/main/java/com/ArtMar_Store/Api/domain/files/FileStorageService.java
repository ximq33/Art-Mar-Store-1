package com.ArtMar_Store.Api.domain.files;

import com.ArtMar_Store.Api.infrastructure.FileStorageRepository;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
public class FileStorageService {


    private final FileStorageRepository fileStorageRepository;
    private final Supplier<ImageId> imageIdSupplier;


    public FileStorageService(FileStorageRepository fileStorageRepository, Supplier<ImageId> imageIdSupplier) {
        this.fileStorageRepository = fileStorageRepository;
        this.imageIdSupplier = imageIdSupplier;
    }


    public Image storeFile(MultipartFile file, String productId) throws IOException {
        Binary binary = new Binary(file.getBytes());
        Image image = new Image(imageIdSupplier.get(), binary, productId, ".webp");
        return fileStorageRepository.save(image);
    }


    public List<ImageResponseDto> loadImagesByProductIds(List<String> productIds) {
        List<ImageResponseDto> images = new ArrayList<>();
        for (String s : productIds) {
            images.add(ImageResponseDto.fromDomain(fileStorageRepository.getImagesByProductId(s)));
        }
        return images;
    }
}
