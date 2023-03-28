package com.ArtMar_Store.Api.api.users;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public record UserRequestDto(

        @Length(min = 3, max = 30, message = "name must contain minimum of 3 characters and maximum of 30 characters")
        String name,
        @Email(message = "email not valid")
        String email,
        @Length(min = 8, message = "password must contain minimum of 8 characters")
        String password

) {
}
