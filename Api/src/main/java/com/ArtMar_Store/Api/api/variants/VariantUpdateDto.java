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
import java.util.Optional;

class VariantUpdateDto{
        @Positive(message = "price has to be positive")
        BigDecimal price;
        @PositiveOrZero(message = "quantity cannot be negative")
        int quantity;
        boolean disabled;
        String imgPath;
        @Length(min = 1, max = 100)
        String manufacturer;
        Color color;
        @Length(min = 1, max = 100)
        String side;
        @Length(min = 1, max = 100)
        String pattern;
        @Length(min = 1, max = 100)
        String productId;

        public Optional<BigDecimal> getPrice() {
                return Optional.ofNullable(price);
        }

        public Optional<Integer> getQuantity() {
                return Optional.ofNullable(Integer.valueOf(quantity));
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

        public Optional<Color> getColor() {
                return Optional.ofNullable(color);
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


}
