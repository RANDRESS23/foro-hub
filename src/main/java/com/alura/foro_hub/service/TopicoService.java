package com.alura.foro_hub.service;

import com.alura.foro_hub.entity.TopicoEntity;
import com.alura.foro_hub.exception.NotFoundException;
import com.alura.foro_hub.mapper.ITopicoEntityMapper;
import com.alura.foro_hub.model.CustomPage;
import com.alura.foro_hub.model.Topico;
import com.alura.foro_hub.repository.ITopicoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TopicoService {
    private final ITopicoRepository topicoRepository;
    private final ITopicoEntityMapper topicoEntityMapper;
    private final UsuarioService usuarioService;
    private final CursoService cursoService;

    public Topico saveTopico(Topico topico, HttpServletRequest HTTPRequest) {
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus(Boolean.TRUE);
        topico.setAutor(usuarioService.getUsuario(HTTPRequest));
        topico.setCurso(cursoService.getCurso(topico.getCurso().getId()));

        TopicoEntity topicoEntity = topicoRepository.save(topicoEntityMapper.toTopicoEntity(topico));
        return topicoEntityMapper.toTopico(topicoEntity);
    }

    public CustomPage<Topico> getAllTopicos(Integer page, Integer size, Boolean ascending) {
        Sort sort = Boolean.TRUE.equals(ascending) ? Sort.by("titulo").ascending() : Sort.by("titulo").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<TopicoEntity> pageTopicosEntity = topicoRepository.findAll(pageable);
        Page<Topico> pageTopicos = topicoEntityMapper.toPageOfTopicos(pageTopicosEntity);

        CustomPage<Topico> customPage = new CustomPage<>();
        customPage.setPageNumber(pageTopicos.getNumber());
        customPage.setPageSize(pageTopicos.getSize());
        customPage.setTotalElements(pageTopicos.getTotalElements());
        customPage.setTotalPages(pageTopicos.getTotalPages());
        customPage.setContent(pageTopicos.getContent());

        return customPage;
    }

    public Topico getTopico(Long id) {
        TopicoEntity topicoEntity = topicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se pudo encontrar el topico con el id #" + id));

        return topicoEntityMapper.toTopico(topicoEntity);
    }

    public Topico updateTopico(Topico topico, Topico topicoRequest) {
        topico.setTitulo(topicoRequest.getTitulo());
        topico.setMensaje(topicoRequest.getMensaje());
        topico.setCurso(cursoService.getCurso(topicoRequest.getCurso().getId()));

        TopicoEntity topicoEntity = topicoRepository.save(topicoEntityMapper.toTopicoEntity(topico));
        return topicoEntityMapper.toTopico(topicoEntity);
    }

    public void deleteTopico(Long id) {
        TopicoEntity topicoEntity = topicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se pudo encontrar el topico con el id #" + id));
        topicoRepository.delete(topicoEntity);
    }
}
