package com.pay.vortexpay.dtos.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.pay.vortexpay.shared.UserRole;

public record UserResponseDTO(
    UUID id,
    String email,
    String fullName,
    String document,
    String phoneNumber,
    UserRole role,
    LocalDateTime createdAt
) {}
