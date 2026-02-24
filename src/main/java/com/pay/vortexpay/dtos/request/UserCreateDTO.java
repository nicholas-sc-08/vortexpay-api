package com.pay.vortexpay.dtos.request;

import java.util.UUID;

import com.pay.vortexpay.shared.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(
    @Email 
    String email,
    
    @NotBlank @Size(min = 6, message = "Password must have 6 characters or more!")
    String password,
    
    @NotNull(message = "User role must be ADMIN or USER.")
    UserRole role,

    @NotNull(message = "Customer ID is required.")
    UUID customerId
) {}