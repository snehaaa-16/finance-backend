package com.sneha.finance.service.impl;

import com.sneha.finance.dto.request.LoginRequest;
import com.sneha.finance.dto.request.RegisterRequest;
import com.sneha.finance.entity.User;
import com.sneha.finance.enums.Role;
import com.sneha.finance.repository.UserRepository;
import com.sneha.finance.security.JwtUtil;
import com.sneha.finance.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_VIEWER)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    private final JwtUtil jwtUtil;

    @Override
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }
}