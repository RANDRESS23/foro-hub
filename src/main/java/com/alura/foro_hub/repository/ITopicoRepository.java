package com.alura.foro_hub.repository;

import com.alura.foro_hub.entity.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicoRepository extends JpaRepository<TopicoEntity, Long>  {
}
