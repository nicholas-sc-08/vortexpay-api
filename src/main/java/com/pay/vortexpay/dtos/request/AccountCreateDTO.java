package com.pay.vortexpay.dtos.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record AccountCreateDTO(
    @NotNull(message = "Customer id must not be null")
    UUID customerId
) {}
