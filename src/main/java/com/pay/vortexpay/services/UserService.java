package com.pay.vortexpay.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pay.vortexpay.dtos.response.UserResponseDTO;
import com.pay.vortexpay.mappers.UserMapper;
import com.pay.vortexpay.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll().stream().map(user -> userMapper.toUserResponse(user)).collect(Collectors.toList());
    }
}
