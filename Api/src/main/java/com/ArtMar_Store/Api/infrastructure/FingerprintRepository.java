package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.api.auth.Fingerprint;
import com.ArtMar_Store.Api.domain.users.UserId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FingerprintRepository extends MongoRepository<Fingerprint, UserId> {

    Optional<Fingerprint> findFingerprintByUserId(UserId userId);
}
