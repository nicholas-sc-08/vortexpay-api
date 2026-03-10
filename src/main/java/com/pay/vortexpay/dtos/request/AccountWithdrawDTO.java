package com.pay.vortexpay.dtos.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotNull;

public record AccountWithdrawDTO(
    @NotNull(message = "Withdraw cannot be null.")
    @Negative(message = "Withdraw must be an negative number to withdraw.")
    BigDecimal balance
) {}
