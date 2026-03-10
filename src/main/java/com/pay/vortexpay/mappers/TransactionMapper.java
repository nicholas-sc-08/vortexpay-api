package com.pay.vortexpay.mappers;

import org.springframework.stereotype.Component;

import com.pay.vortexpay.dtos.request.TransactionCreateDTO;
import com.pay.vortexpay.dtos.response.TransactionResponseDTO;
import com.pay.vortexpay.entities.Transaction;

@Component
public class TransactionMapper {

    public Transaction toTransactionEntity(TransactionCreateDTO dto) {
        Transaction transaction = new Transaction();

        transaction.setAmount(dto.amount());
        transaction.setTransactionType(dto.transactionType());
        transaction.setDescription(dto.description());

        return transaction;
    }

    public TransactionResponseDTO toTransactionResponse(Transaction transaction) {
        return new TransactionResponseDTO(
            transaction.getId(),
            transaction.getSourceAccount().getId(),
            transaction.getDestinationAccount().getId(),
            transaction.getAmount(),
            transaction.getTransactionType(),
            transaction.getDescription(),
            transaction.getCreatedAt()
        );
    }
}
