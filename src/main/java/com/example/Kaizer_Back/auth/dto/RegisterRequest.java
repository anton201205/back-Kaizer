package com.example.Kaizer_Back.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @Email @NotBlank String email,
        @NotBlank @Size(min = 8, max = 100) String password,
        @NotBlank @Size(min = 3, max = 100) String nombre,
        @NotBlank @Size(min = 6, max = 20) String telefono,
        @NotBlank @Size(min = 3, max = 200) String distrito,
        @NotBlank @Size(min = 6, max = 20) String dni
) {
}
