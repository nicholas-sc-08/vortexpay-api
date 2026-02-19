package com.pay.vortexpay.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.vortexpay.dtos.response.CustomerResponseDTO;
import com.pay.vortexpay.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    public ResponseEntity<List<CustomerResponseDTO>> findAllCustomers() {
        List<CustomerResponseDTO> customers = customerService.findAllCustomers();
        return ResponseEntity.status(200).body(customers);
    }
}
