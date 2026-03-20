package com.pay.vortexpay.dtos.request;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionDepositDTO(
    @NotNull(message = "Score Account cannot be null.")
    UUID sourceAccount,

    @NotNull(message = "Destination Account cannot be null.")
    UUID destinationAccount,

    @NotNull(message = "Amount cannot be null.")
    @Positive(message = "Value of Transaction must be positive.")
    BigDecimal amount,

    @NotBlank(message = "Insert a valid description to the transaction.")
    String description
) {}
