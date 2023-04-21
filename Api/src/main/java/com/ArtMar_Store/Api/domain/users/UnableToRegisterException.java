package com.ArtMar_Store.Api.domain.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UnableToRegisterException extends IllegalStateException{
    public UnableToRegisterException(String message) {
        super(message);
    }
}
