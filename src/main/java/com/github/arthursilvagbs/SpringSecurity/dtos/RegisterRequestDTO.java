package com.github.arthursilvagbs.SpringSecurity.dtos;

public record RegisterRequestDTO(
        String nome,
        String email,
        String senha
) {
}
