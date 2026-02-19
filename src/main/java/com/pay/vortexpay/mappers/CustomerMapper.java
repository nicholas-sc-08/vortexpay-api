package com.pay.vortexpay.mappers;

import org.springframework.stereotype.Component;

import com.pay.vortexpay.dtos.request.CustomerCreateDTO;
import com.pay.vortexpay.dtos.response.CustomerResponseDTO;
import com.pay.vortexpay.entities.Customer;

@Component
public class CustomerMapper {
    public Customer toCustomerEntity(CustomerCreateDTO dto) {
        Customer customer = new Customer();
        
        customer.setFullName(dto.fullName());
        customer.setDocument(dto.document());
        customer.setPhoneNumber(dto.phoneNumber());

        return customer;
    }

    public CustomerResponseDTO toCustomerResponse(Customer customer) {
        return new CustomerResponseDTO(
            customer.getId(),
            customer.getFullName(),
            customer.getDocument(),
            customer.getPhoneNumber(),
            customer.getCreatedAt()
        );
    }
}
