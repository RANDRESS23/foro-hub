package com.alura.foro_hub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioRequest {
    private String nombre;
    private String correoElectronico;
    private List<Long> perfilesId;
}
