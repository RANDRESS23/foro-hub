package com.alura.foro_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespuestaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "respuesta_id")
    private Long id;

    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private TopicoEntity topico;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity autor;

    @Column(name = "solucion", nullable = false)
    private String solucion;
}
