package com.alura.foro_hub.service;

import com.alura.foro_hub.config.jwtconfiguration.JwtService;
import com.alura.foro_hub.entity.UsuarioEntity;
import com.alura.foro_hub.exception.AlreadyExistsFieldException;
import com.alura.foro_hub.exception.InvalidCredentials;
import com.alura.foro_hub.mapper.IUsuarioEntityMapper;
import com.alura.foro_hub.model.Usuario;
import com.alura.foro_hub.repository.IUsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String validateUser(Usuario usuario) {
        if (getUserByCorreoElectronico(usuario.getCorreoElectronico()).isPresent()) {
            throw new AlreadyExistsFieldException("¡El correo electronico ya se encuentra registrado!");
        }

        return encodePassword(usuario.getContrasena());
    }

    public Optional<Usuario> getUserByCorreoElectronico(String correoElectronico) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreoElectronico(correoElectronico).orElse(null);
        return Optional.ofNullable(usuarioEntityMapper.toUsuario(usuarioEntity));
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Usuario saveUsuario(Usuario usuario) {
        String encryptedPassword = validateUser(usuario);
        usuario.setContrasena(encryptedPassword);

        UsuarioEntity usuarioEntity = usuarioRepository.save(usuarioEntityMapper.toUsuarioEntity(usuario));
        return usuarioEntityMapper.toUsuario(usuarioEntity);
    }

    public String login(String correoElectronico, String contrasena) {
        if (validateCredentials(correoElectronico, contrasena)) {
            throw new InvalidCredentials("¡Correo electrónico o contraseña incorrectas!");
        }

        Usuario usuario = authenticate(correoElectronico, contrasena);
        return generateToken(usuario);
    }

    public boolean validateCredentials(String correoElectronico, String contrasena) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreoElectronico(correoElectronico).orElse(null);

        if (usuarioEntity == null) return false;

        return passwordEncoder.matches(contrasena, usuarioEntity.getContrasena());
    }

    public Usuario authenticate(String correoElectronico, String contrasena) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        correoElectronico,
                        contrasena
                )
        );

        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreoElectronico(correoElectronico).orElseThrow();

        return usuarioEntityMapper.toUsuario(usuarioEntity);
    }

    public String generateToken(Usuario usuario) {
        return jwtService.generateToken(generateExtraClaims(usuario), usuario);
    }

    private Map<String, Object> generateExtraClaims(Usuario usuario) {
        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("usuarioId", usuario.getId());
        extraClaims.put("nombre", usuario.getNombre());

        return extraClaims;
    }

    public Usuario getUsuario(HttpServletRequest HTTPRequest) {
        String token = jwtService.getToken(HTTPRequest);
        String correoElectronico = jwtService.extractEmail(token);
        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreoElectronico(correoElectronico).orElse(null);

        return usuarioEntityMapper.toUsuario(usuarioEntity);
    }
}
