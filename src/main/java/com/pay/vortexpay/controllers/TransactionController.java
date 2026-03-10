package com.pay.vortexpay.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.vortexpay.dtos.response.TransactionResponseDTO;
import com.pay.vortexpay.services.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> findAllTransactions() {
        List<TransactionResponseDTO> transactions = transactionService.findAllTransactions();
        return ResponseEntity.status(200).body(transactions);
    }
}
