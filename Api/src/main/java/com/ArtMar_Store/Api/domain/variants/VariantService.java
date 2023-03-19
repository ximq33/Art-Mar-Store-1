package com.ArtMar_Store.Api.domain.variants;

import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.products.alreadyExistsException;
import com.ArtMar_Store.Api.infrastructure.VariantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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

    public void deleteVariantById(VariantId variantId) {
        variantRepository.deleteById(variantId);
    }

    public Optional<Variant> getVariantById(String id){
        return variantRepository.findById(new VariantId(id));
    }

    public Optional<Variant> updateVariant(
            String variantId,
            Optional<BigDecimal> priceUpdate,
            Optional<Integer> quantityUpdate,
            Optional<Boolean> disabledUpdate,
            Optional<String> imgPathUpdate,
            Optional<String> manufacturerUpdate,
            Optional<Color> colorUpdate,
            Optional<String> sideUpdate,
            Optional<String> patternUpdate,
            Optional<String> productIdUpdate
    ){
        Optional<ProductId> productIdOpt = productIdUpdate.map(ProductId::new);
        return variantRepository.findById(new VariantId(variantId))
                .map(oldVariant ->
                        new Variant(new VariantId(variantId),
                                priceUpdate.orElseGet(oldVariant::price),
                                quantityUpdate.orElseGet(oldVariant::quantity),
                                disabledUpdate.orElseGet(oldVariant::disabled),
                                imgPathUpdate.orElseGet(oldVariant::imgPath),
                                manufacturerUpdate.orElseGet(oldVariant::manufacturer),
                                colorUpdate.orElseGet(oldVariant::color),
                                sideUpdate.orElseGet(oldVariant::side),
                                patternUpdate.orElseGet(oldVariant::pattern),
                                productIdOpt.orElseGet(oldVariant::productId)
                        )
                )
                .map(variantRepository::save);
    }


}
