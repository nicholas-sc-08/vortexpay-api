package com.pay.vortexpay.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.vortexpay.dtos.response.AccountResponseDTO;
import com.pay.vortexpay.services.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    @Operation(summary = "List all Accounts", description = "Return all the accounts of the system.")
    public ResponseEntity<List<AccountResponseDTO>> findAllAccounts() {
        List<AccountResponseDTO> accounts = accountService.findAllAccounts();
        return ResponseEntity.status(200).body(accounts);        
    }
}
