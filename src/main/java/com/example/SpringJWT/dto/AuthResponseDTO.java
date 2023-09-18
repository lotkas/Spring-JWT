package com.example.SpringJWT.dto;

import org.jetbrains.annotations.NotNull;

public class AuthResponseDTO {

    private String token;

    public AuthResponseDTO(@NotNull String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
