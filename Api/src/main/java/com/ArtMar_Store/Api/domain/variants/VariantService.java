package com.ArtMar_Store.Api.domain.variants;

import com.ArtMar_Store.Api.domain.products.Product;
import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.products.ProductService;
import com.ArtMar_Store.Api.domain.products.alreadyExistsException;
import com.ArtMar_Store.Api.infrastructure.VariantRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class VariantService {

    private final VariantRepository variantRepository;
    private final Supplier<VariantId> variantIdSupplier;

    private final ProductService productService;

    public VariantService(VariantRepository variantRepository, Supplier<VariantId> variantIdSupplier, ProductService productService) {
        this.variantRepository = variantRepository;
        this.variantIdSupplier = variantIdSupplier;
        this.productService = productService;
    }

    public List<Variant> getAllVariants(){
        return variantRepository.findAll();
    }

    public Variant registerNewVariant(BigDecimal price, int quantity, boolean disabled,
                                      String manufacturer, Color color, String side, String pattern,
                                      ProductId productId, String productName){

        Product product = productService.findById(productId).orElseThrow();

        if (product.price() == null || product.price().compareTo(price) > 0){
            productService.updateProduct(productId, Optional.empty(), Optional.empty(), Optional.of(price), Optional.empty(), Optional.empty());
        }


        if (!variantRepository.existsByManufacturerAndColorAndSideAndPatternAndProductId(manufacturer, color, side, pattern, productId)){
            return variantRepository.save(new Variant(variantIdSupplier.get(),price, quantity, disabled,
                    manufacturer, color, side, pattern, productId, productName, LocalDateTime.now()));
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
            Optional<String> manufacturerUpdate,
            Optional<String> colorNameUpdate,
            Optional<String> RGBUpdate,
            Optional<String> sideUpdate,
            Optional<String> patternUpdate,
            Optional<String> productIdUpdate,
            Optional<String> productNameUpdate
    ){

        Optional<ProductId> productIdOpt = productIdUpdate.map(ProductId::new);


        return variantRepository.findById(new VariantId(variantId))
                .map(oldVariant ->
                        new Variant(new VariantId(variantId),
                                priceUpdate.orElseGet(oldVariant::price),
                                quantityUpdate.orElseGet(oldVariant::quantity),
                                disabledUpdate.orElseGet(oldVariant::enabled),
                                manufacturerUpdate.orElseGet(oldVariant::manufacturer),
                                new Color(colorNameUpdate.orElse(oldVariant.color().colorName()), RGBUpdate.orElse(oldVariant.color().RGBvalue())),
                                sideUpdate.orElseGet(oldVariant::side),
                                patternUpdate.orElseGet(oldVariant::pattern),
                                productIdOpt.orElseGet(oldVariant::productId),
                                productNameUpdate.orElseGet(oldVariant::productName),
                                oldVariant.addedDate()
                        )
                )
                .map(variantRepository::save);
    }


    public List<Variant> getVariantsByProductId(String productId) {
        return variantRepository.findByProductId(new ProductId(productId));
    }
}
