package com.github.arthursilvagbs.SpringSecurity.controller;

import com.github.arthursilvagbs.SpringSecurity.dtos.LoginRequestDTO;
import com.github.arthursilvagbs.SpringSecurity.dtos.LoginResponseDTO;
import com.github.arthursilvagbs.SpringSecurity.dtos.RegisterRequestDTO;
import com.github.arthursilvagbs.SpringSecurity.dtos.RegisterResponseDTO;
import com.github.arthursilvagbs.SpringSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> registerUser(@RequestBody RegisterRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return null;
    }

}
