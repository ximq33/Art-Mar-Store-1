package com.ArtMar_Store.Api.api.users;

import com.ArtMar_Store.Api.domain.users.AppUser;
import com.ArtMar_Store.Api.domain.users.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
class UserController {

    private final AppUserService userService;

    public UserController(AppUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<UserResponseDto> registerNewUser(
            @RequestBody UserRequestDto userRequestDto) {
        AppUser user = userService.registerNewUser(userRequestDto);

        return ResponseEntity.created(URI.create("/users" + user.userId().value()))
                .body(UserResponseDto.fromDomain(user));
    }
}
