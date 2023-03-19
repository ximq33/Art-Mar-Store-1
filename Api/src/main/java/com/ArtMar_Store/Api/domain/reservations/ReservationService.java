package com.ArtMar_Store.Api.domain.reservations;

import com.ArtMar_Store.Api.domain.users.UserId;
import com.ArtMar_Store.Api.infrastructure.ReservationRepository;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final Supplier<ReservationId> reservationIdSupplier;


    public ReservationService(ReservationRepository reservationRepository, Supplier<ReservationId> reservationIdSupplier) {
        this.reservationRepository = reservationRepository;
        this.reservationIdSupplier = reservationIdSupplier;
    }

    public List<Reservation> getReservationList() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(ReservationId id) {
        return reservationRepository.findById(id);
    }

    public void deleteReservation(ReservationId id) {
        reservationRepository.findById(id)
                .ifPresent(reservation -> reservationRepository.deleteById(reservation.id()));
    }

    public Reservation registerNewReservation(Cart cart) {
        return reservationRepository.save(new Reservation(reservationIdSupplier.get(),
                LocalDateTime.now(),
                cart,
                new UserId("1")));
    }
}
