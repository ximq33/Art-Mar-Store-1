package com.ArtMar_Store.Api.domain.variants;

import com.ArtMar_Store.Api.api.variants.WidthOption;
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

    public List<Variant> getAllVariants() {
        return variantRepository.findAll();
    }

    public Variant registerNewVariant(String variantName, BigDecimal price, int quantity, boolean disabled,
                                      String manufacturer, Color color, VariantOptions variantOptions,
                                      ProductId productId, String productName) {

        Product product = productService.findById(productId).orElseThrow();

        if (product.price() == null || product.price().compareTo(price) > 0) {
            productService.updateProduct(productId, Optional.empty(), Optional.empty(), Optional.of(price), Optional.empty(), Optional.empty());
        }


        if (!variantRepository.existsByManufacturerAndVariantOptionsAndProductId(manufacturer, variantOptions, productId)) {
            return variantRepository.save(new Variant(variantIdSupplier.get(),variantName, price, quantity, disabled,
                    manufacturer, color, variantOptions, productId, productName, LocalDateTime.now()));
        }
        throw new alreadyExistsException("Same variant already exists");
    }

    public void deleteVariantById(VariantId variantId) {
        variantRepository.deleteById(variantId);
    }

    public Optional<Variant> getVariantById(String id) {
        return variantRepository.findById(new VariantId(id));
    }


    public Optional<Variant> updateVariant(
            String variantId,
            Optional<String> variantNameUpdate,
            Optional<BigDecimal> priceUpdate,
            Optional<Integer> quantityUpdate,
            Optional<Boolean> disabledUpdate,
            Optional<String> manufacturerUpdate,
            Optional<List<WidthOption>> leftUpdate,
            Optional<List<WidthOption>> rightUpdate,
            Optional<String> colorNameUpdate,
            Optional<String> RGBUpdate,
            Optional<String> productIdUpdate,
            Optional<String> productNameUpdate
    ) {

        Optional<ProductId> productIdOpt = productIdUpdate.map(ProductId::new);

        DoorOptions oldDoorOptions = (DoorOptions) variantRepository.findById(new VariantId(variantId)).orElseThrow().variantOptions();

        return variantRepository.findById(new VariantId(variantId))
                .map(oldVariant ->
                        new Variant(new VariantId(variantId),
                                variantNameUpdate.orElseGet(oldVariant::variantName),
                                priceUpdate.orElseGet(oldVariant::price),
                                quantityUpdate.orElseGet(oldVariant::quantity),
                                disabledUpdate.orElseGet(oldVariant::enabled),
                                manufacturerUpdate.orElseGet(oldVariant::manufacturer),
                                new Color(colorNameUpdate.orElse(oldVariant.color().colorName()), RGBUpdate.orElse(oldVariant.color().RGBvalue())),
                                new DoorOptions(leftUpdate.orElse(oldDoorOptions.left()),
                                        rightUpdate.orElse(oldDoorOptions.right())
                                ),
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
