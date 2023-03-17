package com.ArtMar_Store.Api.domain.products;

import com.ArtMar_Store.Api.infrastructure.ProductRepository;
import com.ArtMar_Store.Api.infrastructure.VariantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final VariantRepository variantRepository;

    private final Supplier<ProductId> productIdSupplier;


    public ProductService(ProductRepository productRepository, VariantRepository variantRepository, Supplier<ProductId> productIdSupplier) {
        this.productRepository = productRepository;
        this.variantRepository = variantRepository;
        this.productIdSupplier = productIdSupplier;
    }

    public List<Product> getProductList() {return productRepository.findAll();}

    public void deleteProduct(ProductId id) {
        productRepository.findById(id).ifPresent(s->productRepository.deleteById(s.productId()));
    }

    public Optional<Product> findById(ProductId id) {return productRepository.findById(id);}

    private List<Product> findWithSameManufacturer(ProductId id) {

        String manufacturer = findById(id).get().manufacturer();


        List<Product> list = productRepository.findAll();
        List<Product> sameManufacturers = new ArrayList<>();

        for (Product p: list) {
            if(Objects.equals(p.manufacturer(), manufacturer) && p.productId()!=id ){
                sameManufacturers.add(p);
            }
        }
        return sameManufacturers;
    }

    public Optional<Product> updateProduct(ProductId id,
                                           Optional<String> name,
                                           Optional<String> manufacturer,
                                           Optional<BigDecimal> price,
                                           Optional<String> description)
    {
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
        List<Product> products = productRepository.findAll();
        String productManufacturer = findById(id).get().manufacturer();
        Random random = new Random();
        int max = sameManufacturers.size();
        int randomNumber;

        List<Product> readyResponse = new ArrayList<>();

        while(readyResponse.size() !=4 ) {
            if(max == 0) {
                int newMax = products.size();
                randomNumber = random.nextInt(newMax);
                if(Objects.equals(products.get(randomNumber).manufacturer(), productManufacturer ))
                {
                    products.remove(randomNumber);
                }
                else {
                    readyResponse.add(products.get(randomNumber));
                }


            } else if (max>0) {
                randomNumber = random.nextInt(max);
                readyResponse.add(sameManufacturers.get(randomNumber));
                sameManufacturers.remove(randomNumber);
                max = sameManufacturers.size();
            }
        }

        return readyResponse;

    }
    public Product registerNewProduct(String name, String manufacturer, BigDecimal price, String imgPath, String description) {

        if(!productRepository.existsByNameAndManufacturer(name,manufacturer)) {
            return productRepository.save(new Product(productIdSupplier.get(), name, manufacturer, new ArrayList<>(), price, imgPath, description));
        }
        else throw new alreadyExistsException("Product with same manufacturer already exists");
    }

}
