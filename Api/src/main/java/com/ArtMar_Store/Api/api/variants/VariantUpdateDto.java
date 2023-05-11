package com.ArtMar_Store.Api.api.variants;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

class VariantUpdateDto{
        @Positive(message = "price has to be positive")
        private final BigDecimal price;
        @PositiveOrZero(message = "quantity cannot be negative")
        private final Integer quantity;
        private final Boolean enabled;
        @Length(min = 1, max = 100)
        private final String manufacturer;
        @Length(min = 1, max = 100)
        private final String colorName;
        @Length(min = 1, max = 100)
        private final String RGBvalue;
        @Length(min = 1, max = 100)
        private final String side;
        @Length(min = 1, max = 100)
        private final String pattern;
        @Length(min = 1, max = 100)
        private final String productId;


        public VariantUpdateDto(BigDecimal price, Integer quantity, Boolean disabled, String manufacturer, String colorName, String RGBvalue, String side, String pattern, String productId) {
                this.price = price;
                this.quantity = quantity;
                this.enabled = disabled;
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
                return Optional.ofNullable(enabled);
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
                return Objects.equals(price, that.price) && Objects.equals(quantity, that.quantity) && Objects.equals(enabled, that.enabled) && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(colorName, that.colorName) && Objects.equals(RGBvalue, that.RGBvalue) && Objects.equals(side, that.side) && Objects.equals(pattern, that.pattern) && Objects.equals(productId, that.productId);
        }

        @Override
        public int hashCode() {
                return Objects.hash(price, quantity, enabled, manufacturer, colorName, RGBvalue, side, pattern, productId);
        }

        @Override
        public String toString() {
                return "VariantUpdateDto{" +
                        "price=" + price +
                        ", quantity=" + quantity +
                        ", enabled=" + enabled +
                        ", manufacturer='" + manufacturer + '\'' +
                        ", colorName='" + colorName + '\'' +
                        ", RGBvalue='" + RGBvalue + '\'' +
                        ", side='" + side + '\'' +
                        ", pattern='" + pattern + '\'' +
                        ", productId='" + productId + '\'' +
                        '}';
        }
}
