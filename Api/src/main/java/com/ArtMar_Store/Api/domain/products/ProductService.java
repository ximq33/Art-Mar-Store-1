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

    public void deleteProduct(ProductId id) {productRepository.deleteById(id);}

    public Product findById(ProductId id) {return productRepository.findById(id).get();}

    private List<Product> findWithSameManufacturer(ProductId id) {
        String manufacturer = findById(id).manufacturer();

        List<Product> list = productRepository.findAll();
        List<Product> sameManufacturers = new ArrayList<>();

        for (Product p: list) {
            if(p.manufacturer() == manufacturer && p.productId()!=id ){
                sameManufacturers.add(p);
            }
        }
        return sameManufacturers;
    }

    public List<Product> findRelated(ProductId id) {
        List<Product> sameManufacturers = findWithSameManufacturer(id);
        List<Product> types = productRepository.findAll();
        Random random = new Random();
        int max = sameManufacturers.size();
        int randomNumber;

        List<Product> readyResponse = new ArrayList<>();

        while(readyResponse.size() !=4 ) {
            if(max == 0) {
                int newMax = types.size();
                randomNumber = random.nextInt(newMax);
                if(types.get(randomNumber).manufacturer() == findById(id).manufacturer())
                {
                    types.remove(randomNumber);
                }
                else {
                    readyResponse.add(types.get(randomNumber));
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
            Product product = productRepository.save(new Product(productIdSupplier.get(), name, manufacturer, new ArrayList<>(), price, imgPath, description));
            return product;
        }
        else throw new alreadyExistsException("Product with same manufacturer already exists");
    }

}
