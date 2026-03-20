package com.pay.vortexpay.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.vortexpay.dtos.request.TransactionDepositDTO;
import com.pay.vortexpay.dtos.request.TransactionWithdrawDTO;
import com.pay.vortexpay.dtos.response.TransactionResponseDTO;
import com.pay.vortexpay.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    @Operation(summary = "List all transactions", description = "Retrieves a complete list of all financial records and movements within the system.")
    public ResponseEntity<List<TransactionResponseDTO>> findAllTransactions() {
        List<TransactionResponseDTO> transactions = transactionService.findAllTransactions();
        return ResponseEntity.status(200).body(transactions);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find transaction by ID", description = "Fetches the details of a specific transaction (digital receipt) using its unique UUID.")
    public ResponseEntity<TransactionResponseDTO> findTransactionById(@PathVariable UUID id) {
        TransactionResponseDTO transaction = transactionService.findTransactionById(id);
        return ResponseEntity.status(200).body(transaction);
    }

    @PostMapping("/deposit")
    @Operation(summary = "Execute account deposit", description = "Creates a new transaction record of type 'DEPOSIT', increments the destination account balance, and generates a persistent audit log.")
    public ResponseEntity<TransactionResponseDTO> transactionDepositAccount(@RequestBody @Valid TransactionDepositDTO dto) {
        TransactionResponseDTO transaction = transactionService.transactionDepositAccount(dto);
        return ResponseEntity.status(201).body(transaction);
    }

    @PostMapping("/withdraw")
    @Operation(summary = "Execute account withdraw", description = "Decreases the balance of a specific account by creating a 'WITHDRAW' transaction record. Validates if the account exists and has sufficient funds before processing.")
    public ResponseEntity<TransactionResponseDTO> transactionWithdrawAccount(@RequestBody @Valid TransactionWithdrawDTO dto) {
        TransactionResponseDTO transaction = transactionService.transactionWithdrawAccount(dto);
        return ResponseEntity.status(201).body(transaction);
    }
}
