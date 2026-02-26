package com.pay.vortexpay.mappers;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.pay.vortexpay.dtos.request.CustomerCreateDTO;
import com.pay.vortexpay.dtos.request.CustomerUpdateDTO;
import com.pay.vortexpay.dtos.response.CustomerResponseDTO;
import com.pay.vortexpay.dtos.response.UserResponseDTO;
import com.pay.vortexpay.entities.Customer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final UserMapper userMapper;

    public Customer toCustomerEntity(CustomerCreateDTO dto) {
        Customer customer = new Customer();
        
        customer.setFullName(dto.fullName());
        customer.setDocument(dto.document());
        customer.setPhoneNumber(dto.phoneNumber());

        return customer;
    }

    public Customer toCustomerEntity(CustomerUpdateDTO dto) {
        Customer customer = new Customer();

        customer.setFullName(dto.fullName());
        customer.setDocument(dto.document());
        customer.setPhoneNumber(dto.phoneNumber());

        return customer;
    }

    public CustomerResponseDTO toCustomerResponse(Customer customer) {
        UserResponseDTO user = customer.getUser() != null ? userMapper.toUserResponse(customer.getUser()) : null;

        return new CustomerResponseDTO(
            customer.getId(),
            customer.getFullName(),
            customer.getDocument(),
            customer.getPhoneNumber(),
            customer.getCreatedAt(),
            user
        );
    }
}
