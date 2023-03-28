package com.ArtMar_Store.Api.api.reservations;

import com.ArtMar_Store.Api.api.products.ProductResponseDto;
import com.ArtMar_Store.Api.domain.reservations.Reservation;
import com.ArtMar_Store.Api.domain.reservations.ReservationId;
import com.ArtMar_Store.Api.domain.reservations.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ReservationController.reservation_baseURL)
class ReservationController {

    private final ReservationService reservationService;

    ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
        return ResponseEntity.ok(
                reservationService.getReservationList()
                        .stream()
                        .map(ReservationResponseDto::fromDomain)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    ResponseEntity<ReservationResponseDto> getReservation(@PathVariable String id) {
        return ResponseEntity.of(reservationService.findById(new ReservationId(id))
                .map(ReservationResponseDto::fromDomain));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ReservationResponseDto> deleteReservation(@PathVariable String id) {
        reservationService.deleteReservation(new ReservationId(id));
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    ResponseEntity<ReservationResponseDto> registerNewReservation(
            @RequestBody ReservationRequestDto requestDto) {
        Reservation reservation = reservationService.registerNewReservation(requestDto.cart());
        return ResponseEntity.created(URI.create("/reservations" + reservation.id().value()))
                .body(ReservationResponseDto.fromDomain(reservation));
    }



    static final String reservation_baseURL = "/reservations";
}
