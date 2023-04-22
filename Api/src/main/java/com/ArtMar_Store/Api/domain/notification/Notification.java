package com.ArtMar_Store.Api.domain.notification;

import com.ArtMar_Store.Api.domain.users.UserId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document
public record Notification(
        @MongoId
        NotificationId notificationId,
        UserId userId,
        String title,
        String content,
        LocalDateTime dateTime,
        boolean viewed
        ) {
}
