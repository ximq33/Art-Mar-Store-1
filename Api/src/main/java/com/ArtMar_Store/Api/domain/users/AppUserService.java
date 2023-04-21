package com.ArtMar_Store.Api.domain.users;

import com.ArtMar_Store.Api.api.users.UserRequestDto;
import com.ArtMar_Store.Api.api.users.UserResponseDto;
import com.ArtMar_Store.Api.infrastructure.AppUserRepository;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    private final Supplier<UserId> userIdSupplier;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    public AppUserService(AppUserRepository appUserRepository, Supplier<UserId> userIdSupplier) {
        this.appUserRepository = appUserRepository;
        this.userIdSupplier = userIdSupplier;

    }

    public List<UserDetails> findAll() {
        List<AppUser> appUsers = appUserRepository.findAll();

        return new ArrayList<>(appUsers);
    }

    public AppUser registerNewUser(UserRequestDto userRequestDto) {
        if (appUserRepository.existsAccountByEmail(userRequestDto.email())){
            throw new UnableToRegisterException("Użytkownik z podanym e-mailem już istnieje");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return appUserRepository.save(new AppUser(userIdSupplier.get(),
                userRequestDto.name(),
                userRequestDto.email(),
                encoder.encode(userRequestDto.password()),
                new SimpleGrantedAuthority("ADMIN"),
                true));  //TODO userBuilder (?)
    }

    public UserDetails findByUserName(String username) {
        return appUserRepository.findUserDetailsByName(username);
    }

    public List<UserResponseDto> getAllUsers() {
        return appUserRepository.findAll().stream().map(UserResponseDto::fromDomain).toList();
    }

    public Optional<UserResponseDto> findAppUserByName(String name){
        return Optional.of(appUserRepository.findAppUserByName(name).map(UserResponseDto::fromDomain)).get();
    }

    public Optional<UserResponseDto> findAppUserByUserId(String userId) {
        return appUserRepository.findAppUserByUserId(UserId.newId(userId)).map(UserResponseDto::fromDomain);
    }

    public AppUser mapJwtToUser(Jwt jwt){
        String id = jwt.getClaim("userId").toString();
        return appUserRepository.findAppUserByUserId(UserId.newId(id)).get();
    }
}
