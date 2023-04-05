package com.ArtMar_Store.Api.api.products;

import com.ArtMar_Store.Api.domain.products.Product;
import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.products.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.ArtMar_Store.Api.api.products.ProductController.product_baseURL;

@RestController
@RequestMapping(product_baseURL)
@CrossOrigin
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(
                productService.getProductList()
                        .stream()
                        .map(ProductResponseDto::fromDomain)
                        .collect(Collectors.toList()));
    }

    @PostMapping
    ResponseEntity<ProductResponseDto> registerNewProduct(
            @RequestBody ProductRequestDto requestDto) {
        Product product = productService.registerNewProduct(
                requestDto.name(),
                requestDto.manufacturer(),
                requestDto.price(),
                "1.webp",
                requestDto.description());
        return ResponseEntity.created(URI.create("/products/" + product.productId().value())
        ).body(
                ProductResponseDto.fromDomain(product)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(new ProductId(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDto> getProduct(@PathVariable String id) {
        return ResponseEntity.of(productService.findById(new ProductId(id))
                .map(ProductResponseDto::fromDomain));
    }

    @GetMapping("/relatedOrRandom/{id}")
    ResponseEntity<List<ProductResponseDto>> getRelatedProducts(@PathVariable String id) {
        return ResponseEntity.ok(
                productService.findRelatedOrRandom(new ProductId(id))
                        .stream()
                        .map(ProductResponseDto::fromDomain)
                        .collect(Collectors.toList()));
    }


static final String product_baseURL = "/products";
}
