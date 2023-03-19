package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.domain.reservations.Reservation;
import com.ArtMar_Store.Api.domain.reservations.ReservationId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, ReservationId> {

}
