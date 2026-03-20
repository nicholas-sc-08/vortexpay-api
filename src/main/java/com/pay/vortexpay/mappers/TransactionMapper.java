package com.pay.vortexpay.mappers;

import org.springframework.stereotype.Component;

import com.pay.vortexpay.dtos.request.TransactionDepositDTO;
import com.pay.vortexpay.dtos.request.TransactionWithdrawDTO;
import com.pay.vortexpay.dtos.response.TransactionResponseDTO;
import com.pay.vortexpay.entities.Account;
import com.pay.vortexpay.entities.Transaction;
import com.pay.vortexpay.shared.TransactionType;

@Component
public class TransactionMapper {

    public Transaction toTransactionEntity(TransactionDepositDTO dto, Account sourceAccount, Account destinationAccount) {
        Transaction transaction = new Transaction();

        transaction.setDestinationAccount(destinationAccount);
        transaction.setSourceAccount(sourceAccount);

        transaction.setAmount(dto.amount());
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setDescription(dto.description());

        return transaction;
    }

    public Transaction toTransactionEntity(TransactionWithdrawDTO dto, Account sourceAccount, Account destinationAccount) {
        Transaction transaction = new Transaction();

        transaction.setDestinationAccount(destinationAccount);
        transaction.setSourceAccount(sourceAccount);

        transaction.setAmount(dto.amount());
        transaction.setTransactionType(TransactionType.WITHDRAW);
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
