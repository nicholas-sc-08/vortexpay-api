package com.pay.vortexpay.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.vortexpay.dtos.request.UserCreateDTO;
import com.pay.vortexpay.dtos.response.UserResponseDTO;
import com.pay.vortexpay.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
        List<UserResponseDTO> users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable UUID id) {
        UserResponseDTO user = userService.findUserById(id);
        return ResponseEntity.status(200).body(user);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO dto) {
        UserResponseDTO user = userService.createUser(dto);
        return ResponseEntity.status(201).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(204).build();
    }
}
