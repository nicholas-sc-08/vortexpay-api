package com.pay.vortexpay.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.vortexpay.dtos.request.CustomerCreateDTO;
import com.pay.vortexpay.dtos.request.CustomerUpdateDTO;
import com.pay.vortexpay.dtos.response.CustomerResponseDTO;
import com.pay.vortexpay.services.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "List all Customers", description = "Return all customers of the system.")
    public ResponseEntity<List<CustomerResponseDTO>> findAllCustomers() {
        List<CustomerResponseDTO> customers = customerService.findAllCustomers();
        return ResponseEntity.status(200).body(customers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Customer by ID", description = "Recover complete details of an specific customer by it's UUID. Return error 404 if the identificator has not been found.")
    public ResponseEntity<CustomerResponseDTO> findCustomerById(@PathVariable UUID id) {
        CustomerResponseDTO customer = customerService.findCustomerById(id);
        return ResponseEntity.status(200).body(customer);
    }

    @PostMapping
    @Operation(summary = "Create new Customer", description = "Create a new customer account on the VortexPay. The document and phone number must be unique.")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerCreateDTO dto) {
        CustomerResponseDTO customer = customerService.createCustomer(dto);
        return ResponseEntity.status(201).body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable UUID id, @RequestBody CustomerUpdateDTO dto) {
        CustomerResponseDTO customer = customerService.updateCustomer(id, dto);
        return ResponseEntity.status(200).body(customer);   
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Customer by ID", description = "Delete the customer by ID, if the customer with the param ID does not exists, show error 404.")
    public ResponseEntity<?> deleteCustomerById(@PathVariable UUID id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
