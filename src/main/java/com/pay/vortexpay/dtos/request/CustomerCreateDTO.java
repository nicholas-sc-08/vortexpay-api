package com.pay.vortexpay.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CustomerCreateDTO(
    @NotBlank(message = "Please insert your full name.")
    String fullName,

    @NotBlank(message = "Document field can not be empty!")
    String document,

    @NotBlank(message = "Phone Number field can not be empty!")
    String phoneNumber
) {}
