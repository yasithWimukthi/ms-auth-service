package com.ecommerce.msauthservice.service;

import com.ecommerce.msauthservice.domain.UserAuth;
import com.ecommerce.msauthservice.dto.AuthResponse;
import com.ecommerce.msauthservice.dto.LoginRequest;
import com.ecommerce.msauthservice.dto.SignupRequest;
import com.ecommerce.msauthservice.repository.UserAuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    private final UserAuthRepository repo;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserAuthRepository repo, JwtService jwtService) {
        this.repo = repo;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public AuthResponse signup(SignupRequest req) {
        if (repo.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email already exists");
        }
        var user = UserAuth.builder()
                .email(req.email())
                .password(passwordEncoder.encode(req.password()))
                .role("USER")
                .build();
        repo.save(user);

        var token = jwtService.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest req) {
        var user = repo.findByEmail(req.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        var token = jwtService.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token);
    }
}