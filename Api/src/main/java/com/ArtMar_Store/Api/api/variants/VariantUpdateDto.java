package com.ArtMar_Store.Api.api.variants;

import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.variants.Color;
import com.ArtMar_Store.Api.domain.variants.Variant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

class VariantUpdateDto{
        @Positive(message = "price has to be positive")
        BigDecimal price;
        @PositiveOrZero(message = "quantity cannot be negative")
        Integer quantity;
        Boolean disabled;
        String imgPath;
        @Length(min = 1, max = 100)
        String manufacturer;
        @Length(min = 1, max = 100)
        String colorName;
        @Length(min = 1, max = 100)
        String RGBvalue;
        @Length(min = 1, max = 100)
        String side;
        @Length(min = 1, max = 100)
        String pattern;
        @Length(min = 1, max = 100)
        String productId;


        public VariantUpdateDto(BigDecimal price, Integer quantity, Boolean disabled, String imgPath, String manufacturer, String colorName, String RGBvalue, String side, String pattern, String productId) {
                this.price = price;
                this.quantity = quantity;
                this.disabled = disabled;
                this.imgPath = imgPath;
                this.manufacturer = manufacturer;
                this.colorName = colorName;
                this.RGBvalue = RGBvalue;
                this.side = side;
                this.pattern = pattern;
                this.productId = productId;
        }

        public Optional<BigDecimal> getPrice() {
                return Optional.ofNullable(price);
        }

        public Optional<Integer> getQuantity() {
                return Optional.ofNullable(quantity);
        }

        public Optional<Boolean> isDisabled() {
                return Optional.ofNullable(disabled);
        }

        public Optional<String> getImgPath() {
                return Optional.ofNullable(imgPath);
        }

        public Optional<String> getManufacturer() {
                return Optional.ofNullable(manufacturer);
        }

        public Optional<String> getColorName() {
                return Optional.ofNullable(colorName);
        }

        public Optional<String> getRGBvalue() {
                return Optional.ofNullable(RGBvalue);
        }

        public Optional<String> getSide() {
                return Optional.ofNullable(side);
        }

        public Optional<String> getPattern() {
                return Optional.ofNullable(pattern);
        }

        public Optional<String> getProductId() {
                return Optional.ofNullable(productId);
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                VariantUpdateDto that = (VariantUpdateDto) o;
                return quantity == that.quantity && disabled == that.disabled && Objects.equals(price, that.price) && Objects.equals(imgPath, that.imgPath) && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(colorName, that.colorName) && Objects.equals(RGBvalue, that.RGBvalue) && Objects.equals(side, that.side) && Objects.equals(pattern, that.pattern) && Objects.equals(productId, that.productId);
        }

        @Override
        public int hashCode() {
                return Objects.hash(price, quantity, disabled, imgPath, manufacturer, colorName, RGBvalue, side, pattern, productId);
        }

        @Override
        public String toString() {
                return "VariantUpdateDto{" +
                        "price=" + price +
                        ", quantity=" + quantity +
                        ", disabled=" + disabled +
                        ", imgPath='" + imgPath + '\'' +
                        ", manufacturer='" + manufacturer + '\'' +
                        ", colorName='" + colorName + '\'' +
                        ", RGBvalue='" + RGBvalue + '\'' +
                        ", side='" + side + '\'' +
                        ", pattern='" + pattern + '\'' +
                        ", productId='" + productId + '\'' +
                        '}';
        }
}
