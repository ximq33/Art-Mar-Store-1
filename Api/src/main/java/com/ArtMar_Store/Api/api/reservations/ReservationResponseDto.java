package com.ArtMar_Store.Api.api.reservations;

import com.ArtMar_Store.Api.api.products.ProductResponseDto;
import com.ArtMar_Store.Api.domain.reservations.Cart;
import com.ArtMar_Store.Api.domain.reservations.Reservation;

import java.time.format.DateTimeFormatter;

public record ReservationResponseDto(
        String reservationId,
        String dateTime,
        Cart cart,
        String userId
) {

    static ReservationResponseDto fromDomain(Reservation reservation) {
        return new ReservationResponseDto(
                reservation.id().value(),
                reservation.dateTime().format(DateTimeFormatter.ISO_DATE_TIME),
                reservation.cart(),
                reservation.userId().value());
    }
}
