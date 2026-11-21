package com.github.arthursilvagbs.SpringSecurity.dtos;

public record LoginRequestDTO(
        String email,
        String senha
) {
}
