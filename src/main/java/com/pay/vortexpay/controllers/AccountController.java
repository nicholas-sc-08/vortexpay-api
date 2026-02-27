package com.pay.vortexpay.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.vortexpay.dtos.request.AccountStatusDTO;
import com.pay.vortexpay.dtos.response.AccountResponseDTO;
import com.pay.vortexpay.services.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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

    @PatchMapping("/{id}")
    public ResponseEntity<?> changeAccountStatus(@PathVariable UUID id, @RequestBody @Valid AccountStatusDTO dto) {
        accountService.changeAccountStatus(id, dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Account by Customer ID", description = "Removes the account associated with the provided Customer ID. "+"**WARNING**: This is a destructive operation that triggers a cascading delete, "+"permanently removing the associated Customer and User records from the database.")    
    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteAccountByCustomerId(UUID id) {
        accountService.deleteAccountByCustomerId(id);
        return ResponseEntity.noContent().build();
    }
}
