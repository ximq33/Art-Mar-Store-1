package com.ArtMar_Store.Api.api.variants;

import com.ArtMar_Store.Api.api.products.ErrorDTO;
import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.products.ProductService;
import com.ArtMar_Store.Api.domain.products.alreadyExistsException;
import com.ArtMar_Store.Api.domain.variants.Variant;
import com.ArtMar_Store.Api.domain.variants.VariantId;
import com.ArtMar_Store.Api.domain.variants.VariantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ArtMar_Store.Api.api.variants.VariantController.variant_baseURL;

@RestController
@RequestMapping(variant_baseURL)
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
class VariantController {

    private final VariantService variantService;
    private final ProductService productService;

    public VariantController(VariantService variantService, ProductService productService) {
        this.variantService = variantService;
        this.productService = productService;
    }

    @GetMapping
    ResponseEntity<List<VariantResponseDto>> getAllVariants(){
        return ResponseEntity.ok(variantService.getAllVariants().stream().map(VariantResponseDto::fromDomain).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    ResponseEntity<VariantResponseDto> getVariantById(@PathVariable String id){
        return ResponseEntity.of(variantService.getVariantById(id).map(VariantResponseDto::fromDomain));
    }

    @PostMapping
    ResponseEntity<VariantResponseDto> registerNewVariant(
            @RequestBody VariantRequestDto requestDto
    ) {


        Variant variant = variantService.registerNewVariant(
                requestDto.variantName(),
                requestDto.price(),
                requestDto.quantity(),
                requestDto.enabled(),
                requestDto.manufacturer(),
                requestDto.color(),
                requestDto.doorOptions(),
                requestDto.productId(),
                productService.findById(requestDto.productId()).orElseThrow().name()
        );
        return ResponseEntity.created(URI.create("/variants/" + variant.variantId().value()))
                .body(VariantResponseDto.fromDomain(variant));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<VariantResponseDto> deleteVariant(@PathVariable String id){
        variantService.deleteVariantById(new VariantId(id));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<VariantResponseDto> updateVariant(@PathVariable String id, @Valid @RequestBody VariantUpdateDto updateDto){

        Optional<String> productNameOpt = Optional.of(productService.findById(new ProductId(updateDto.getProductId().orElseThrow())).orElseThrow().name());

        return ResponseEntity.of(variantService.updateVariant(id, updateDto.getVariantName(), updateDto.getPrice(), updateDto.getQuantity(), updateDto.isDisabled(),
                updateDto.getManufacturer(), updateDto.getLeftOptions(), updateDto.getRightOptions(),updateDto.getColorName(), updateDto.getRGBvalue(),
                updateDto.getProductId(), productNameOpt).map(VariantResponseDto::fromDomain));
    }

    @GetMapping("/byProductId/{productId}")
    ResponseEntity<List<VariantResponseDto>> getVariantsByProductId(@PathVariable String productId) {
        List<VariantResponseDto> variants = variantService.getVariantsByProductId(productId)
                .stream()
                .map(VariantResponseDto::fromDomain)
                .collect(Collectors.toList());
        if (variants.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(variants);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(alreadyExistsException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(alreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorDTO.newOf(ex.getMessage(),
                HttpStatus.CONFLICT,
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
    }


    static final String variant_baseURL = "/variants";
}
