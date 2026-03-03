package com.pay.vortexpay.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.vortexpay.dtos.request.AccountCreateDTO;
import com.pay.vortexpay.dtos.request.AccountDepositDTO;
import com.pay.vortexpay.dtos.request.AccountStatusDTO;
import com.pay.vortexpay.dtos.response.AccountResponseDTO;
import com.pay.vortexpay.services.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @Operation(summary = "List all Accounts", description = "Return all the accounts of the system.")
    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> findAllAccounts() {
        List<AccountResponseDTO> accounts = accountService.findAllAccounts();
        return ResponseEntity.status(200).body(accounts);        
    }

    @Operation(summary = "Find Unique Account by Id", description = "Recover complete details of an specific user by it's UUID. Return error 404 if the identificator has not been found.")
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> findAccountById(@PathVariable UUID id) {
        AccountResponseDTO account = accountService.findAccountById(id);
        return ResponseEntity.status(200).body(account);
    }

    @Operation(summary = "Create new Account", description = "Creates a new financial account linked to a customer. "+"**Note:** Each customer can only hold one active account at a time.")
    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody @Valid AccountCreateDTO dto) {
        AccountResponseDTO account = accountService.createNewAccount(dto);
        return ResponseEntity.status(201).body(account);
    }

    @Operation(summary = "Update Account status", description = "Changes the status of an existing account (e.g., **ACTIVE**, **INACTIVE** or **BLOCKED**)."+"This is a partial update and does not affect other account details.")
    @PatchMapping("/status/{id}")
    public ResponseEntity<?> changeAccountStatus(@PathVariable UUID id, @RequestBody @Valid AccountStatusDTO dto) {
        accountService.changeAccountStatus(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/deposit/{id}")
    public ResponseEntity<AccountResponseDTO> depositBalanceAccount(@PathVariable UUID id, @RequestBody @Valid AccountDepositDTO dto) {
        AccountResponseDTO account = accountService.depositBalanceAccount(id, dto);
        return ResponseEntity.status(200).body(account);
    }

    @Operation(summary = "Delete Account by Customer ID", description = "Removes the account associated with the provided Customer ID. "+"**WARNING**: This is a destructive operation that triggers a cascading delete, "+"permanently removing the associated Customer and User records from the database.")    
    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteAccountByCustomerId(@PathVariable UUID customerId) {
        accountService.deleteAccountByCustomerId(customerId);
        return ResponseEntity.noContent().build();
    }
}
