package com.pay.vortexpay.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pay.vortexpay.dtos.request.CustomerCreateDTO;
import com.pay.vortexpay.dtos.response.CustomerResponseDTO;
import com.pay.vortexpay.entities.Customer;
import com.pay.vortexpay.exceptions.CustomerNotFoundException;
import com.pay.vortexpay.exceptions.CustomerWithDocumentAlreadyExistsException;
import com.pay.vortexpay.exceptions.CustomerWithPhoneNumberAlreadyExistsException;
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

    public CustomerResponseDTO findCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with ID "+id+" does not exists!"));
        return customerMapper.toCustomerResponse(customer);
    }

    public CustomerResponseDTO createCustomer(CustomerCreateDTO dto) {
        customerRepository.findCustomerByDocument(dto.document()).ifPresent(c -> { throw new CustomerWithDocumentAlreadyExistsException("Customer with document "+dto.document()+" already exists!"); });
        customerRepository.findCustomerByPhoneNumber(dto.phoneNumber()).ifPresent(c -> { throw new CustomerWithPhoneNumberAlreadyExistsException("Customer with phone number "+dto.phoneNumber()+" already exists!"); });

        Customer customer = customerMapper.toCustomerEntity(dto);
        Customer newCustomer = customerRepository.save(customer);

        return customerMapper.toCustomerResponse(newCustomer);
    }

    public void deleteCustomerById(UUID id) {
        customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with ID "+id+" does not exists!"));
        customerRepository.deleteById(id);
    }
}
