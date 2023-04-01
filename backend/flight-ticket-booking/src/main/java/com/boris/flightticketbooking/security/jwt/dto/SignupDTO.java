package com.boris.flightticketbooking.security.jwt.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupDTO {

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 60)
    private String password;
    @NotBlank
    @Size(min = 2, max = 60)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 60)
    private String lastName;

}
