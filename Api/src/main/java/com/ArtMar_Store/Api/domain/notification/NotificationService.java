package com.ArtMar_Store.Api.domain.notification;

import com.ArtMar_Store.Api.infrastructure.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final Supplier<NotificationId> notificationIdSupplier;

    public NotificationService(NotificationRepository notificationRepository, Supplier<NotificationId> notificationIdSupplier) {
        this.notificationRepository = notificationRepository;
        this.notificationIdSupplier = notificationIdSupplier;
    }



    public void createReservationNotification(){

    }
}
