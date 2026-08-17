package org.marosandor.backend.dto.auth;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class RegisterRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, message = "A jelszónak legalább 8 karakter hosszúságúnak kell lennie")
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
