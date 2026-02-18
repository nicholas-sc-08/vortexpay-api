package com.pay.vortexpay.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pay.vortexpay.dtos.response.UserResponseDTO;
import com.pay.vortexpay.dtos.request.UserCreateDTO;
import com.pay.vortexpay.entities.User;
import com.pay.vortexpay.exceptions.EmailAlreadyExistsException;
import com.pay.vortexpay.exceptions.UserNotFoundException;
import com.pay.vortexpay.mappers.UserMapper;
import com.pay.vortexpay.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll().stream().map(user -> userMapper.toUserResponse(user)).collect(Collectors.toList());
    }

    public UserResponseDTO findUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with UUID "+id+" does not exists!"));
        return userMapper.toUserResponse(user);
    }

    public UserResponseDTO createUser(UserCreateDTO dto) {
        userRepository.findUserByEmail(dto.email()).ifPresent((user) -> {
            throw new EmailAlreadyExistsException("User with email "+dto.email()+" already exists!");
        });
        User user = userMapper.toUserEntity(dto);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(savedUser);
    }

    public void deleteUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with UUID "+id+" does not exists!"));
        userRepository.delete(user);
    }
}
