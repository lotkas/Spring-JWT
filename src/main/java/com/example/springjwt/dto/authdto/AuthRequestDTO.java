package com.example.springjwt.dto.authdto;

import org.jetbrains.annotations.NotNull;

public class AuthRequestDTO {

    private String login;

    private String password;

    public AuthRequestDTO(@NotNull String login,
                          @NotNull String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthRequestDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
