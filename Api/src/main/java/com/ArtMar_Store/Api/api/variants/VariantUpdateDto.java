package com.ArtMar_Store.Api.api.variants;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

class VariantUpdateDto{
        @Length(min = 1, max = 100)

        private final String variantName;
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
        private final List<WidthOption> leftOptions;
        private final List<WidthOption> rightOptions;
        @Length(min = 1, max = 100)
        private final String productId;


        public VariantUpdateDto(String variantName, BigDecimal price, Integer quantity, Boolean disabled, String manufacturer, String colorName, String RGBvalue, List<WidthOption> leftOptions, List<WidthOption> rightOptions, String productId) {
                this.variantName = variantName;
                this.price = price;
                this.quantity = quantity;
                this.enabled = disabled;
                this.manufacturer = manufacturer;
                this.colorName = colorName;
                this.RGBvalue = RGBvalue;
                this.leftOptions = leftOptions;
                this.rightOptions = rightOptions;
                this.productId = productId;
        }

        public Optional<String> getVariantName(){
                return Optional.ofNullable(variantName);
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
        public Optional<List<WidthOption>> getLeftOptions(){
                return Optional.ofNullable(leftOptions);
        }
        public Optional<List<WidthOption>> getRightOptions(){
                return Optional.ofNullable(rightOptions);
        }


        public Optional<String> getProductId() {
                return Optional.ofNullable(productId);
        }


        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                VariantUpdateDto that = (VariantUpdateDto) o;
                return Objects.equals(variantName, that.variantName) && Objects.equals(price, that.price) && Objects.equals(quantity, that.quantity) && Objects.equals(enabled, that.enabled) && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(colorName, that.colorName) && Objects.equals(RGBvalue, that.RGBvalue) && Objects.equals(leftOptions, that.leftOptions) && Objects.equals(rightOptions, that.rightOptions) && Objects.equals(productId, that.productId);
        }

        @Override
        public int hashCode() {
                return Objects.hash(variantName, price, quantity, enabled, manufacturer, colorName, RGBvalue, leftOptions, rightOptions, productId);
        }

        @Override
        public String toString() {
                return "VariantUpdateDto{" +
                        "variantName='" + variantName + '\'' +
                        ", price=" + price +
                        ", quantity=" + quantity +
                        ", enabled=" + enabled +
                        ", manufacturer='" + manufacturer + '\'' +
                        ", colorName='" + colorName + '\'' +
                        ", RGBvalue='" + RGBvalue + '\'' +
                        ", leftOptions=" + leftOptions +
                        ", rightOptions=" + rightOptions +
                        ", productId='" + productId + '\'' +
                        '}';
        }
}
