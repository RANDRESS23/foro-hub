package com.alura.foro_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topico_id")
    private Long id;

    @Column(name = "titulo", nullable = false, unique = true)
    private String titulo;

    @Column(name = "mensaje", nullable = false, unique = true)
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "estado", nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;
}
