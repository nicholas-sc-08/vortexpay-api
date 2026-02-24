package com.pay.vortexpay.mappers;

import org.springframework.stereotype.Component;

import com.pay.vortexpay.dtos.request.UserCreateDTO;
import com.pay.vortexpay.dtos.request.UserRequestDTO;
import com.pay.vortexpay.dtos.response.CustomerResponseDTO;
import com.pay.vortexpay.dtos.response.UserResponseDTO;
import com.pay.vortexpay.entities.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final CustomerMapper customerMapper;

    public User toUserEntity(UserRequestDTO dto) {
        User user = new User();

        user.setEmail(dto.email());
        user.setPassword(dto.password());

        return user;
    }

    public User toUserEntity(UserCreateDTO dto) {
        User user = new User();

        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());

        return user;
    }

    public UserResponseDTO toUserResponse(User user) {
        CustomerResponseDTO customerDTO = user.getCustomer() != null ? customerMapper.toCustomerResponse(user.getCustomer()) : null;
        
        return new UserResponseDTO(
            user.getId(),
            user.getEmail(),
            user.getRole(),
            customerDTO
        );
    }
}
