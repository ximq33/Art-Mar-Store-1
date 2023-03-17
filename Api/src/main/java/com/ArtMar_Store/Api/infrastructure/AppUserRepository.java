package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.domain.users.AppUser;
import com.ArtMar_Store.Api.domain.users.UserId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUserRepository extends MongoRepository<AppUser, UserId> {

    boolean existsAccountByEmail(String email);
}
