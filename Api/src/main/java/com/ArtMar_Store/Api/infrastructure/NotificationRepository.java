package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.domain.notification.Notification;
import com.ArtMar_Store.Api.domain.notification.NotificationId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, NotificationId> {
}
