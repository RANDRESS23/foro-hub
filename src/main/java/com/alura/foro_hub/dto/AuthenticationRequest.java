package com.alura.foro_hub.dto;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class AuthenticationRequest {
    private final String email;
    private final String password;
}
