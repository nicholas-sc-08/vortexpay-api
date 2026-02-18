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

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "List all users", description = "Return all users of the system.")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
        List<UserResponseDTO> users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @Operation(summary = "Find user by ID", description = "Recover complete details of an specific user by it's UUID. Return error 404 if the identificator has not been found.")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable UUID id) {
        UserResponseDTO user = userService.findUserById(id);
        return ResponseEntity.status(200).body(user);
    }

    @Operation(summary = "Create new user", description = "Create a new access to the VortexPay. The email must be unique. At this moment, the 'Customer' (Banking Profile) is optional and can be completed in onboarding.")
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO dto) {
        UserResponseDTO user = userService.createUser(dto);
        return ResponseEntity.status(201).body(user);
    }

    @Operation(summary = "Delete user by ID", description = "Delete permanently the credentials of user access. This opreation it's irreversible and follows the LGPD guidelines to delete the data of account.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(204).build();
    }
}
