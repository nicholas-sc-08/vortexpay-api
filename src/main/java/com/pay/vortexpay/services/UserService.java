package com.pay.vortexpay.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pay.vortexpay.dtos.request.UserCreateDTO;
import com.pay.vortexpay.dtos.request.UserUpdateDTO;
import com.pay.vortexpay.dtos.response.UserResponseDTO;
import com.pay.vortexpay.entities.Customer;
import com.pay.vortexpay.entities.User;
import com.pay.vortexpay.exceptions.CustomerNotFoundException;
import com.pay.vortexpay.exceptions.EmailAlreadyExistsException;
import com.pay.vortexpay.exceptions.UserNotFoundException;
import com.pay.vortexpay.mappers.UserMapper;
import com.pay.vortexpay.repositories.CustomerRepository;
import com.pay.vortexpay.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    private void encryptPassword(User user, String dtoPassword) {
        if(!dtoPassword.isBlank()) {
            String safePassword = passwordEncoder.encode(dtoPassword);
            user.setPassword(safePassword);
        }
    }

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

        Customer customer = customerRepository.findById(dto.customerId()).orElseThrow(() -> new CustomerNotFoundException("Customer with ID "+dto.customerId()+" does not exists!"));
        
        User user = userMapper.toUserEntity(dto, customer);

        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);

        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(savedUser);
    }

    public UserResponseDTO updateUser(UUID id, UserUpdateDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID "+id+" does not exists!"));

        userRepository.findUserByEmail(dto.email()).ifPresent(u -> {
            throw new EmailAlreadyExistsException("User with email"+dto.email()+" already exists!");
        });
        
        user.setEmail(dto.email());
        encryptPassword(user, dto.password());
        
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    public void deleteUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with UUID "+id+" does not exists!"));
        userRepository.delete(user);
    }
}
