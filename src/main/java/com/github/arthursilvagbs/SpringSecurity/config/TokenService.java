package com.github.arthursilvagbs.SpringSecurity.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.arthursilvagbs.SpringSecurity.domain.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
public class TokenService {

    private String secret = "secret";

    public String generateToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        Instant now = Instant.now();

        return JWT.create()
                .withClaim("userId", user.getIdUsuario().toString())
                .withSubject(user.getEmail())
                .withExpiresAt(now.plusSeconds(120))
                .withIssuedAt(now)
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decode = JWT.require(algorithm).build().verify(token);

            return Optional.of(new JWTUserData(
                    UUID.fromString(decode.getClaim("userId").asString()),
                    decode.getSubject()
            ));

        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}
