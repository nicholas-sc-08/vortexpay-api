package com.pay.vortexpay.mappers;

import org.springframework.stereotype.Component;

import com.pay.vortexpay.dtos.request.UserRequestDTO;
import com.pay.vortexpay.dtos.response.UserResponseDTO;
import com.pay.vortexpay.entities.User;

@Component
public class UserMapper {
    public User toUserEntity(UserRequestDTO dto) {
        User user = new User();

        user.setEmail(dto.email());
        user.setPassword(dto.password());

        return user;
    }

    public UserResponseDTO toUserResponse(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getEmail(),
            user.getCustomer().getFullName(),
            user.getCustomer().getDocument(),
            user.getRole()
        );
    }
}
