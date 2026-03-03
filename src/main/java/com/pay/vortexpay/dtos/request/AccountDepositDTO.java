package com.pay.vortexpay.dtos.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AccountDepositDTO(
    @NotNull(message = "Deposit number it's required!")
    @Positive(message = "Deposit must be an positive number to deposit!")
    BigDecimal balance
) {}
