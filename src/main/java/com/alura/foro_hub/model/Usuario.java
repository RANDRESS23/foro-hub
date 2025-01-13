package com.alura.foro_hub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private List<Perfil> perfiles;
}
