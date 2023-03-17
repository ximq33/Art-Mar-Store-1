package com.ArtMar_Store.Api.domain.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class alreadyExistsException extends RuntimeException {

    public alreadyExistsException(String message) {
        super(message);
    }

    public alreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
