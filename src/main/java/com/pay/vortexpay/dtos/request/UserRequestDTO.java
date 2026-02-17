package com.pay.vortexpay.dtos.request;

public record UserRequestDTO(
    String email,
    String password,
    String fullName,
    String document
) {}
