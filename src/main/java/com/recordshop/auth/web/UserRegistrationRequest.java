package com.recordshop.auth.web;

import com.recordshop.auth.validation.Password;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegistrationRequest {
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;

    @NotBlank
    @Email
    private final String email;
    @NotBlank
    @Password
    private final String password;
}
