package com.ArtMar_Store.Api.domain.products;

import com.ArtMar_Store.Api.infrastructure.ProductRepository;
import com.ArtMar_Store.Api.infrastructure.VariantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final VariantRepository variantRepository;

    private final Supplier<ProductId> productIdSupplier;


    ProductService(ProductRepository productRepository, VariantRepository variantRepository, Supplier<ProductId> productIdSupplier) {
        this.productRepository = productRepository;
        this.variantRepository = variantRepository;
        this.productIdSupplier = productIdSupplier;
    }

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public void deleteProduct(ProductId id) {
        productRepository.findById(id)
                .ifPresent(s -> productRepository.deleteById(s.productId()));
    }

    public Optional<Product> findById(ProductId id) {
        return productRepository.findById(id);
    }

    private List<Product> findWithSameManufacturer(ProductId id) {

        String manufacturer = findById(id).get().manufacturer();

        List<Product> list = productRepository.findAll();
        List<Product> sameManufacturers = new ArrayList<>();

        for (Product p : list) {
            if (p.manufacturer().equals(manufacturer) && !p.productId().value().equals(id.value())) {
                sameManufacturers.add(p);
            }
        }
        return sameManufacturers;
    }

    private List<Product> findWithoutManufacturer(String manufacturer) {
        List<Product> allProducts = productRepository.findAll();
        List<Product> productsWithoutManufacturer = new ArrayList<>();
        for (Product p : allProducts) {
            if (!p.manufacturer().equals(manufacturer)) {
                productsWithoutManufacturer.add(p);
            }
        }
        return productsWithoutManufacturer;
    }

    public Optional<Product> updateProduct(ProductId id,
                                           Optional<String> name,
                                           Optional<String> manufacturer,
                                           Optional<BigDecimal> price,
                                           Optional<String> description) {
        productRepository.findById(id)
                .map(productFromRepository ->
                        new Product(id,
                                name.orElse(productFromRepository.name()),
                                manufacturer.orElse(productFromRepository.manufacturer()),
                                productFromRepository.variants(),
                                price.orElse(productFromRepository.price()),
                                productFromRepository.imgPath(),
                                description.orElse(productFromRepository.description()))
                ).ifPresent(productRepository::save);
        return productRepository.findById(id);
    }

    public List<Product> findRelatedOrRandom(ProductId id) {
        List<Product> sameManufacturers = findWithSameManufacturer(id);
        List<Product> products = findWithoutManufacturer(findById(id).get().manufacturer());
        String productManufacturer = findById(id).get().manufacturer();
        Random random = new Random();
        int max = sameManufacturers.size();
        System.out.println(max);
        int randomNumber;

        List<Product> readyResponse = new ArrayList<>();

        while (readyResponse.size() != 4 || products.size() == 0) {
            if (max == 0) {
                int newMax = products.size();
                randomNumber = random.nextInt(newMax);
                if (Objects.equals(products.get(randomNumber).manufacturer(), productManufacturer)) {
                    products.remove(randomNumber);
                } else {
                    readyResponse.add(products.get(randomNumber));
                }
            } else {
                randomNumber = random.nextInt(max);
                readyResponse.add(sameManufacturers.get(randomNumber));
                sameManufacturers.remove(randomNumber);
                max = sameManufacturers.size();
            }
        }

        return readyResponse;

    }

    public Product registerNewProduct(String name, String manufacturer, BigDecimal price, String imgPath, String description) {

        if (!productRepository.existsByNameAndManufacturer(name, manufacturer)) {
            return productRepository.save(new Product(productIdSupplier.get(), name, manufacturer, new ArrayList<>(), price, imgPath, description));
        } else throw new alreadyExistsException("Product with same manufacturer and name already exists");
    }

}
