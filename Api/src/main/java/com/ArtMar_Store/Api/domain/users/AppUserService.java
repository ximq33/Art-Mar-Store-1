package com.ArtMar_Store.Api.domain.users;

import com.ArtMar_Store.Api.api.users.UserRequestDto;
import com.ArtMar_Store.Api.infrastructure.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return appUserRepository.save(new AppUser(userIdSupplier.get(),
                userRequestDto.name(),
                userRequestDto.email(),
                encoder.encode(userRequestDto.password()),
                new SimpleGrantedAuthority("USER"),
                true));  //TODO userBuilder (?)
    }

    public UserDetails findByUserName(String username) {
        return appUserRepository.findAppUserByName(username);
    }
}
