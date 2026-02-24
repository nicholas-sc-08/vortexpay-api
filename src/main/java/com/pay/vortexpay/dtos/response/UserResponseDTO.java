package com.pay.vortexpay.dtos.response;

import java.util.UUID;

import com.pay.vortexpay.shared.UserRole;

public record UserResponseDTO(
    UUID id,
    String email,
    UserRole role,
    CustomerResponseDTO customer
) {}
