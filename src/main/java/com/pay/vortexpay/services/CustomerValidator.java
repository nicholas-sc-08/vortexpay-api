package com.pay.vortexpay.services;

import org.springframework.stereotype.Component;

import com.pay.vortexpay.dtos.request.CustomerUpdateDTO;
import com.pay.vortexpay.entities.Customer;
import com.pay.vortexpay.exceptions.CustomerWithDocumentAlreadyExistsException;
import com.pay.vortexpay.exceptions.CustomerWithPhoneNumberAlreadyExistsException;
import com.pay.vortexpay.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerValidator {
    private final CustomerRepository customerRepository;

    public void validateUpdate(Customer customer, CustomerUpdateDTO dto) {
        if(!customer.getDocument().equals(dto.document())) {
            customerRepository.findCustomerByDocument(dto.document()).ifPresent(c -> { throw new CustomerWithDocumentAlreadyExistsException("Customer with document "+dto.document()+" already exists!");});
        }

        if(!customer.getPhoneNumber().equals(dto.phoneNumber())) {
            customerRepository.findCustomerByPhoneNumber(dto.phoneNumber()).ifPresent(c -> { throw new CustomerWithPhoneNumberAlreadyExistsException("Customer with phone number "+dto.phoneNumber()+" already exists!");});
        }
    }
}
