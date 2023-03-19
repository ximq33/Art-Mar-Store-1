package com.ArtMar_Store.Api.domain.variants;

import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.products.alreadyExistsException;
import com.ArtMar_Store.Api.infrastructure.VariantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;

@Service
public class VariantService {

    private final VariantRepository variantRepository;
    private final Supplier<VariantId> variantIdSupplier;

    public VariantService(VariantRepository variantRepository, Supplier<VariantId> variantIdSupplier) {
        this.variantRepository = variantRepository;
        this.variantIdSupplier = variantIdSupplier;
    }

    public List<Variant> getAllVariants(){
        return variantRepository.findAll();
    }

    public Variant registerNewVariant(BigDecimal price, int quantity, boolean disabled, String imgPath,
                                      String manufacturer, Color color, String side, String pattern,
                                      ProductId productId){

        if (!variantRepository.existsByManufacturerAndColorAndSideAndPatternAndProductId(manufacturer, color, side, pattern, productId)){
            Variant variant = variantRepository.save(new Variant(variantIdSupplier.get(),price, quantity, disabled, imgPath,
                    manufacturer, color, side, pattern, productId));
            return variant;
        }
        throw new alreadyExistsException("Same variant already exists");
    }

}
