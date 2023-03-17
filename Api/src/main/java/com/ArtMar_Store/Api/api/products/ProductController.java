package com.ArtMar_Store.Api.api.products;

import com.ArtMar_Store.Api.domain.products.Product;
import com.ArtMar_Store.Api.domain.products.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.ArtMar_Store.Api.api.products.ProductController.product_baseURL;

@RestController
@RequestMapping(product_baseURL)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    ResponseEntity<List<ProductResponseDto>> getAllProducts(){
    return ResponseEntity.ok(productService.getProductList().stream().map(ProductResponseDto::fromDomain).collect(Collectors.toList()));
    }

    @PostMapping
    ResponseEntity<ProductResponseDto> registerNewProduct (
            @RequestBody ProductRequestDto requestDto
            ) {
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



    static final String product_baseURL = "/products";
}
