package com.ArtMar_Store.Api.domain.notification;

public record NotificationId(String value) {
    static NotificationId newId(String value) {
        return new NotificationId(value);
    }
}
