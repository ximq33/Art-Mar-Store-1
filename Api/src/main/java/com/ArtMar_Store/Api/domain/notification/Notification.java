package com.ArtMar_Store.Api.domain.notification;

import com.ArtMar_Store.Api.domain.users.UserId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public record Notification(
        @Id
        NotificationId notificationId,
        UserId userId,
        String content,
        LocalDateTime dateTime,
        boolean viewed
        ) {
}
