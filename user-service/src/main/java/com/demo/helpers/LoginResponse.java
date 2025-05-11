package com.demo.helpers;

import com.demo.dtos.UserDTO;

public class LoginResponse {
    private final String jwt;
    private final UserDTO user;

    public LoginResponse(String jwt, UserDTO user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public UserDTO getUser() {
        return user;
    }
}
