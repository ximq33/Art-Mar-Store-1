package com.ArtMar_Store.Api.api.products;

import com.ArtMar_Store.Api.domain.products.Color;
import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.products.Variant;
import com.ArtMar_Store.Api.domain.products.VariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.ArtMar_Store.Api.api.products.VariantController.variant_baseURL;

@RestController
@RequestMapping(variant_baseURL)
public class VariantController {

    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    @GetMapping
    ResponseEntity<List<VariantResponseDto>> getAllVariants(){
        return ResponseEntity.ok(variantService.getAllVariants().stream().map(VariantResponseDto::fromDomain).collect(Collectors.toList()));
    }

    @PostMapping
    ResponseEntity<VariantResponseDto> registerNewVariant(
            @RequestBody VariantRequestDto requestDto
    ) {
        Variant variant = variantService.registerNewVariant(
                requestDto.price(),
                requestDto.quantity(),
                false,
                requestDto.imgPath(),
                requestDto.manufacturer(),
                requestDto.color(),
                requestDto.side(),
                requestDto.pattern(),
                requestDto.productId()
        );
        return ResponseEntity.created(URI.create("/variants/" + variant.variantId().value()))
                .body(
                        VariantResponseDto.fromDomain(variant)
                );
    }


    static final String variant_baseURL = "/variants";
}
