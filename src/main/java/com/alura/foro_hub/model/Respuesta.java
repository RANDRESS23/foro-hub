package com.alura.foro_hub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Respuesta {
    private Long id;
    private String mensaje;
    private Topico topico;
    private LocalDateTime fechaCreacion;
    private Usuario autor;
    private String solucion;
}
