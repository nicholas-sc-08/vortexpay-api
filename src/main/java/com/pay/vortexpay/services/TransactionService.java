package com.pay.vortexpay.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pay.vortexpay.dtos.response.TransactionResponseDTO;
import com.pay.vortexpay.entities.Transaction;
import com.pay.vortexpay.mappers.TransactionMapper;
import com.pay.vortexpay.repositories.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public List<TransactionResponseDTO> findAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(transaction -> transactionMapper.toTransactionResponse(transaction)).collect(Collectors.toList());
    }

    
}
