package com.atlas.loan.application.controllers.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "Email should not be blank.")
    private String email;

    @NotBlank(message = "Password should not be blank.")
    private String password;
}
