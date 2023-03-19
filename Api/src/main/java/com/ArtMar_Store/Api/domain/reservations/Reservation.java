package com.ArtMar_Store.Api.domain.reservations;

import com.ArtMar_Store.Api.domain.users.UserId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public record Reservation(

    @Id ReservationId id,
    LocalDateTime dateTime,
    Cart cart,
    UserId userId

) {
}
