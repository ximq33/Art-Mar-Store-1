package com.ArtMar_Store.Api.infrastructure;

import com.ArtMar_Store.Api.domain.users.AppUser;
import com.ArtMar_Store.Api.domain.users.UserId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AppUserRepository extends MongoRepository<AppUser, UserId> {

    boolean existsAccountByEmail(String email);

    Optional<AppUser> findAppUserByEmail(String email);


    UserDetails findUserDetailsByName(String username);


    Optional<AppUser> findAppUserByName(String name);

    Optional<AppUser> findAppUserByUserId(UserId userId);


    Optional<UserDetails> findUserDetailsByNameOrEmail(String usernameOrEmail, String usernameOrEmail1);

    boolean existsAccountByName(String name);
}
