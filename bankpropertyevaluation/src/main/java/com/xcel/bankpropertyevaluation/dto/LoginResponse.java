package com.xcel.bankpropertyevaluation.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    // Constructor that accepts a token
    public LoginResponse(String token) {
        this.token = token;
    }
}
