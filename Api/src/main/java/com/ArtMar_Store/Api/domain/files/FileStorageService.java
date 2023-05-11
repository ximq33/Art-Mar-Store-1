package com.ArtMar_Store.Api.domain.files;

import com.ArtMar_Store.Api.domain.products.Product;
import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.products.ProductService;
import com.ArtMar_Store.Api.domain.variants.VariantService;
import com.ArtMar_Store.Api.infrastructure.FileStorageRepository;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class FileStorageService {


    private final FileStorageRepository fileStorageRepository;
    private final ProductService productService;
    private final VariantService variantService;
    private final Supplier<ImageId> imageIdSupplier;


    public FileStorageService(FileStorageRepository fileStorageRepository, ProductService productService, VariantService variantService, Supplier<ImageId> imageIdSupplier) {
        this.fileStorageRepository = fileStorageRepository;
        this.productService = productService;
        this.variantService = variantService;
        this.imageIdSupplier = imageIdSupplier;
    }


    public Image storeFile(MultipartFile file, String variantId) throws IOException {
        ImageId imageId = imageIdSupplier.get();
        ProductId productId = variantService.getVariantById(variantId).orElseThrow().productId();
        Product product = productService.findById(productId).orElseThrow();

        if (product.variantImageId() == null){
            productService.updateProduct(productId, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(imageId.value()));

        }

        Binary binary = new Binary(file.getBytes());
        Image image = new Image(imageId, binary, variantId, ".webp");
        return fileStorageRepository.save(image);
    }


    public List<ImageResponseDto> loadImagesByVariantIds(List<String> variantIds) {
        List<ImageResponseDto> images = new ArrayList<>();
        for (String s : variantIds) {
            images.add(ImageResponseDto.fromDomain(fileStorageRepository.getImagesByVariantId(s)));
        }
        return images;
    }


    public List<ImageResponseDto> loadImagesByImageIds(List<String> imageIds) {
        List<ImageResponseDto> images = new ArrayList<>();
        for (String s : imageIds) {
            images.add(ImageResponseDto.fromDomain(fileStorageRepository.getImagesByImageId(new ImageId(s))));
        }
        return images;
    }
}
