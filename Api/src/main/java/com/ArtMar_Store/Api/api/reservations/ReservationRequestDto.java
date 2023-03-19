package com.ArtMar_Store.Api.api.reservations;

import com.ArtMar_Store.Api.domain.reservations.Cart;
import com.ArtMar_Store.Api.domain.users.UserId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservationRequestDto(
        @NotNull(message = "cart cannot be null")
        Cart cart

) {
}
