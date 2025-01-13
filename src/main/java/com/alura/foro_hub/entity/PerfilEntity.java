package com.alura.foro_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perfiles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PerfilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfil_id")
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
}
