package com.ArtMar_Store.Api.api.auth;

public record Token(String value) {
    public static Token newOf(String value){
        return new Token(value);
    }
}
