package com.ecommerce.msauthservice.dto;

import jakarta.validation.constraints.*;

public record SignupRequest(
        @Email @NotBlank String email,
        @NotBlank String password
) {}
