package com.ArtMar_Store.Api.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CookieException extends IllegalStateException{

    public CookieException(String message){
        super(message);
    }

}
