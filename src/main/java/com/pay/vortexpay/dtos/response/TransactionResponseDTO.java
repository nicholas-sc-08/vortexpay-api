package com.pay.vortexpay.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.pay.vortexpay.shared.TransactionType;

public record TransactionResponseDTO(
    UUID id,
    UUID sourceAccount,
    UUID destinationAccount,
    BigDecimal amount,
    TransactionType transactionType,
    String description,
    LocalDateTime createdAt
) {}
