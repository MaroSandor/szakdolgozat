package org.marosandor.backend.dto.auth;

import lombok.*;
import org.marosandor.backend.enums.Role;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String email;
    private String username;
    private Role role;
}
