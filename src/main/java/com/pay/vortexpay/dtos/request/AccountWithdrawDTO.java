package com.pay.vortexpay.dtos.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Negative;

public record AccountWithdrawDTO(
    @Negative(message = "Withdraw must be an negative number to withdraw")
    BigDecimal balance
) {}
