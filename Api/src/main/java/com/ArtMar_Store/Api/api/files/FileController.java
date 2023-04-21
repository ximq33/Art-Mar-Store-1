package com.ArtMar_Store.Api.api.files;

import com.ArtMar_Store.Api.domain.files.FileStorageService;
import com.ArtMar_Store.Api.domain.files.Image;
import com.ArtMar_Store.Api.domain.files.ImageResponseDto;
import com.ArtMar_Store.Api.domain.products.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/files")
@CrossOrigin
class FileController {

    private final FileStorageService fileStorageService;
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    public FileController(FileStorageService fileStorageService, ProductService productService) {
        this.fileStorageService = fileStorageService;
        this.productService = productService;
    }



    @PostMapping("/{productId}")
    public ResponseEntity<Image> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String productId) throws URISyntaxException, IOException {
        Image image = fileStorageService.storeFile(file, productId);
        return ResponseEntity.created(new URI("/files/" + image.imageId().value())).body(image);
    }

    @GetMapping
    public ResponseEntity<List<ImageResponseDto>> getImagesByProductIds(@RequestParam List<String> productIds){
        return ResponseEntity.ok(fileStorageService.loadImagesByProductIds(productIds));
    }

}
