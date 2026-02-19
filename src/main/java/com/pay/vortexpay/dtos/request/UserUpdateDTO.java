package com.pay.vortexpay.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
    @Email 
    String email,

    @Size(min = 6, message = "Password must have 6 characters or more!")
    String password
) {}
