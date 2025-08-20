package com.ecommerce.msauthservice.web;

import com.ecommerce.msauthservice.dto.AuthResponse;
import com.ecommerce.msauthservice.dto.LoginRequest;
import com.ecommerce.msauthservice.dto.SignupRequest;
import com.ecommerce.msauthservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignupRequest req) {
        var res = authService.signup(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        var res = authService.login(req);
        return ResponseEntity.ok(res);
    }
}