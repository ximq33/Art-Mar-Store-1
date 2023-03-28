package com.ArtMar_Store.Api.api.users;

import com.ArtMar_Store.Api.domain.users.AppUser;
import com.ArtMar_Store.Api.domain.users.AppUserService;
import com.ArtMar_Store.Api.domain.users.UserId;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;


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

    @GetMapping
    ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/fromToken")
    ResponseEntity<UserResponseDto> getSingleUser(Authentication authentication){
        return ResponseEntity.of(userService.findAppUserByName(authentication.getName()));
    }

    @GetMapping("/{userId}")
    ResponseEntity<UserResponseDto> getUserById(@PathVariable String userId){
        return ResponseEntity.of(userService.findAppUserByUserId(userId));
    }
}
