package com.pay.vortexpay.dtos.response;

import java.math.BigDecimal;
import java.util.UUID;

import com.pay.vortexpay.shared.AccountStatus;

public record AccountResponseDTO(
    UUID id,
    String accountNumber,
    BigDecimal balance,
    AccountStatus status,
    UUID customerId,
    String customerName
) {}
