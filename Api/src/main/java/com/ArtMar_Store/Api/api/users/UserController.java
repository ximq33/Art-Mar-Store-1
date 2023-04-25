package com.ArtMar_Store.Api.api.users;

import com.ArtMar_Store.Api.api.products.ErrorDTO;
import com.ArtMar_Store.Api.domain.products.alreadyExistsException;
import com.ArtMar_Store.Api.domain.users.AppUser;
import com.ArtMar_Store.Api.domain.users.AppUserService;
import com.ArtMar_Store.Api.domain.users.UnableToRegisterException;
import com.ArtMar_Store.Api.domain.users.UserId;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
class UserController {

    private final AppUserService userService;

    public UserController(AppUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<UserResponseDto> registerNewUser(
          @Valid @RequestBody UserRequestDto userRequestDto) {
        AppUser user = userService.registerNewUser(userRequestDto);

        return ResponseEntity.ok(UserResponseDto.fromDomain(user));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UnableToRegisterException.class)
        public ResponseEntity<ErrorDTO> exceptionHandler(UnableToRegisterException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorDTO.newOf(ex.getMessage(),
                HttpStatus.CONFLICT,
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
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
