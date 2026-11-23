package com.github.arthursilvagbs.SpringSecurity.config;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JWTUserData(
        UUID idUsuario,
        String email
) {
}
