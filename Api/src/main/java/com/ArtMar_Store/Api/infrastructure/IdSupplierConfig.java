package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.domain.files.ImageId;
import com.ArtMar_Store.Api.domain.notification.NotificationId;
import com.ArtMar_Store.Api.domain.products.ProductId;
import com.ArtMar_Store.Api.domain.reservations.ReservationId;
import com.ArtMar_Store.Api.domain.users.UserId;
import com.ArtMar_Store.Api.domain.variants.VariantId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.UUID;
import java.util.function.Supplier;

@Configuration
public class IdSupplierConfig {

    @Bean
    public Supplier<ProductId> productIdSupplier() {return () -> new ProductId(UUID.randomUUID().toString());}


    @Bean
    public Supplier<VariantId> variantIdSupplier() {
        return () -> new VariantId(UUID.randomUUID().toString());
    }

    @Bean
    public Supplier<ReservationId> reservationIdSupplier() {return () -> new ReservationId(UUID.randomUUID().toString());}

    @Bean
    public Supplier<UserId> userIdSupplier() {return () -> new UserId(UUID.randomUUID().toString());}

    @Bean
    public Supplier<ImageId> imageIdSupplier(){
        return () -> new ImageId(UUID.randomUUID().toString());
    }

    @Bean
    public Supplier<NotificationId> notificationIdSupplier(){
        return () -> new NotificationId(UUID.randomUUID().toString());
    }
}
