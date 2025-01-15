package com.alura.foro_hub.controller;

import com.alura.foro_hub.dto.TopicoRequest;
import com.alura.foro_hub.dto.TopicoResponse;
import com.alura.foro_hub.dto.UsuarioRequest;
import com.alura.foro_hub.dto.UsuarioResponse;
import com.alura.foro_hub.mapper.IUsuarioEntityMapper;
import com.alura.foro_hub.model.Topico;
import com.alura.foro_hub.model.Usuario;
import com.alura.foro_hub.repository.IUsuarioRepository;
import com.alura.foro_hub.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final IUsuarioEntityMapper usuarioEntityMapper;

    @PostMapping
    public ResponseEntity<UsuarioResponse> addUsuario(@Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = usuarioEntityMapper.usuarioRequestToUsuario(request);
        Usuario usuarioSaved = usuarioService.saveUsuario(usuario);
        UsuarioResponse response = usuarioEntityMapper.toUsuarioResponse(usuarioSaved);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
