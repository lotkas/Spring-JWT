package com.example.SpringJWT.configurations;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{
    private final String message;

    public ApiException(@NotNull HttpStatus status) {
        this.message = status.getReasonPhrase();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
