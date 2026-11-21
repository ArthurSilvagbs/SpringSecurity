package com.github.arthursilvagbs.SpringSecurity.service;

import com.github.arthursilvagbs.SpringSecurity.config.SecurityConfig;
import com.github.arthursilvagbs.SpringSecurity.domain.User;
import com.github.arthursilvagbs.SpringSecurity.dtos.LoginRequestDTO;
import com.github.arthursilvagbs.SpringSecurity.dtos.LoginResponseDTO;
import com.github.arthursilvagbs.SpringSecurity.dtos.RegisterRequestDTO;
import com.github.arthursilvagbs.SpringSecurity.dtos.RegisterResponseDTO;
import com.github.arthursilvagbs.SpringSecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public RegisterResponseDTO registerUser(RegisterRequestDTO dto) {
        User user = new User(
                dto.nome(),
                dto.email(),
                encoder.encode(dto.senha())
        );

        repository.save(user);
        return new RegisterResponseDTO(user.getNome(), user.getEmail());
    }

    @Transactional
    public LoginResponseDTO login(LoginRequestDTO dto) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.senha()
        );

        Authentication authentication = authenticationManager.authenticate(userAndPass);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado. "));
    }
}

