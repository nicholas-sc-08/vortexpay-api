package com.pay.vortexpay.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    
    @Email(message = "Must be email format!")
    @NotBlank(message = "Please insert your email!")
    String email,

    @NotBlank(message = "Please insert your password!")
    String password
) {}