package com.pay.vortexpay.dtos.request;

import com.pay.vortexpay.shared.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateDTO(
    @Email String email,
    @NotBlank String password,
    @NotNull UserRole role
) {}