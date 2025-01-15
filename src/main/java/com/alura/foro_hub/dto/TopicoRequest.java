package com.alura.foro_hub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopicoRequest {
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensaje;
    @NotNull
    private Long cursoId;
}
