package com.ArtMar_Store.Api.api.files;

import com.ArtMar_Store.Api.domain.files.FileStorageService;
import com.ArtMar_Store.Api.domain.files.Image;
import com.ArtMar_Store.Api.domain.files.ImageResponseDto;
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

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/{variantId}")
    public ResponseEntity<Image> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String variantId) throws URISyntaxException, IOException {
        Image image = fileStorageService.storeFile(file, variantId);
        return ResponseEntity.created(new URI("/files/" + image.imageId().value())).body(image);
    }

    @GetMapping("/ByVariantIds")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ImageResponseDto>> getImagesByVariantIds(@RequestParam List<String> variantId){
        return ResponseEntity.ok(fileStorageService.loadImagesByVariantIds(variantId));
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ImageResponseDto>> getImagesByImageIds(@RequestParam List<String> imageId){
        return ResponseEntity.ok(fileStorageService.loadImagesByImageIds(imageId));
    }

}
