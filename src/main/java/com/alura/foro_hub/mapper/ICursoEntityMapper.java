package com.alura.foro_hub.mapper;

import com.alura.foro_hub.entity.CursoEntity;
import com.alura.foro_hub.model.Curso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICursoEntityMapper {
    Curso toCurso(CursoEntity cursoEntity);
    CursoEntity toCursoEntity(Curso curso);
}
