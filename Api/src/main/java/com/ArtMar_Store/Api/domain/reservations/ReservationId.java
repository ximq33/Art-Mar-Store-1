package com.ArtMar_Store.Api.domain.reservations;

public record ReservationId(String value) {
    static ReservationId newId(String value) {
        return new ReservationId(value);
    }
}
