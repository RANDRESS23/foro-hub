package com.alura.foro_hub.controller;

import com.alura.foro_hub.dto.AuthenticationRequest;
import com.alura.foro_hub.dto.AuthenticationResponse;
import com.alura.foro_hub.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        String token = usuarioService.login(request.getEmail(), request.getPassword());
        AuthenticationResponse response = new AuthenticationResponse(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
