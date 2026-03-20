package com.pay.vortexpay.dtos.request;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionWithdrawDTO (
    @NotNull(message = "Score Account cannot be null.")
    UUID sourceAccount,

    @NotNull(message = "Destination Account cannot be null.")
    UUID destinationAccount,

    @NotNull(message = "Amount cannot be null.")
    @Negative(message = "Value of Transaction must be negative.")
    BigDecimal amount,

    @NotBlank(message = "Insert a valid description to the transaction.")
    String description
) {}
