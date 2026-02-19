package com.pay.vortexpay.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pay.vortexpay.dtos.response.CustomerResponseDTO;
import com.pay.vortexpay.entities.Customer;
import com.pay.vortexpay.mappers.CustomerMapper;
import com.pay.vortexpay.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerResponseDTO> findAllCustomers() {
        List<Customer> customer = customerRepository.findAll();
        return customer.stream().map(c -> customerMapper.toCustomerResponse(c)).collect(Collectors.toList());
    }
}
