package com.alura.foro_hub.dto;

import com.alura.foro_hub.model.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String correoElectronico;
    private List<Perfil> perfiles;
}
