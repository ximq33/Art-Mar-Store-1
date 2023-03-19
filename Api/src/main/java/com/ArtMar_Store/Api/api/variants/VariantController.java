package com.ArtMar_Store.Api.api.variants;

import com.ArtMar_Store.Api.api.products.ErrorDTO;
import com.ArtMar_Store.Api.domain.products.*;
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
public class VariantController {

    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
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

    @DeleteMapping("/{id}")
    ResponseEntity<VariantResponseDto> deleteVariant(@PathVariable String id){
        variantService.deleteVariantById(new VariantId(id));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<VariantResponseDto> updateVariant(@PathVariable String id, @Valid @RequestBody VariantUpdateDto updateDto){

        return ResponseEntity.of(variantService.updateVariant(id, updateDto.getPrice(), updateDto.getQuantity(), updateDto.isDisabled(),
                updateDto.getImgPath(), updateDto.getManufacturer(), updateDto.getColor(), updateDto.getSide(), updateDto.getPattern(),
                updateDto.getProductId()).map(VariantResponseDto::fromDomain));
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
