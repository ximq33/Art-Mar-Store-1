package com.ArtMar_Store.Api.api.notification;

import com.ArtMar_Store.Api.domain.notification.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ArtMar_Store.Api.api.notification.NotificationController.NOTIFICATIONS_BASE_URL;

@RestController
@RequestMapping(NOTIFICATIONS_BASE_URL)
public class NotificationController {

//    @GetMapping
//    ResponseEntity<Notification>

    static final String NOTIFICATIONS_BASE_URL = "/notifications";
}
