package com.alura.foro_hub.repository;

import com.alura.foro_hub.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoRepository extends JpaRepository<CursoEntity, Long> {
}
