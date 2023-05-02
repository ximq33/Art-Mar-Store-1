package com.ArtMar_Store.Api.domain.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WrongCredentialsException extends IllegalStateException{
    public WrongCredentialsException(String message){
        super(message);
    }
}
