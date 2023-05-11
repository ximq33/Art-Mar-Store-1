package com.ArtMar_Store.Api.api.products;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

class ProductUpdateDto {
    private final String name;
    private final String manufacturer;
    private final BigDecimal price;
    private final String description;
    private final String variantImageId;

    public ProductUpdateDto(String name, String manufacturer, BigDecimal price, String description, String variantImageId) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
        this.variantImageId = variantImageId;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getManufacturer() {
        return Optional.ofNullable(manufacturer);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<String> getVariantImageId() {
        return Optional.ofNullable(variantImageId);
    }

    public Optional<BigDecimal> getPrice() {
        return Optional.ofNullable(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductUpdateDto that = (ProductUpdateDto) o;
        return Objects.equals(name, that.name) && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(price, that.price) && Objects.equals(description, that.description) && Objects.equals(variantImageId, that.variantImageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufacturer, price, description, variantImageId);
    }

    @Override
    public String toString() {
        return "ProductUpdateDto{" +
                "name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", variantImageId='" + variantImageId + '\'' +
                '}';
    }
}
