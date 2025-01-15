package com.alura.foro_hub.service;

import com.alura.foro_hub.entity.CursoEntity;
import com.alura.foro_hub.exception.NotFoundException;
import com.alura.foro_hub.mapper.ICursoEntityMapper;
import com.alura.foro_hub.model.Curso;
import com.alura.foro_hub.repository.ICursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final ICursoRepository cursoRepository;
    private final ICursoEntityMapper cursoEntityMapper;

    public Curso getCurso(Long id) {
        CursoEntity cursoEntity = cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se pudo encontrar el curso con el id #" + id));

        return cursoEntityMapper.toCurso(cursoEntity);
    }
}
