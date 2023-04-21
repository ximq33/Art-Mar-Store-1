package com.ArtMar_Store.Api.api.users;

public record Token(String value) {
    public static Token newOf(String value){
        return new Token(value);
    }
}
