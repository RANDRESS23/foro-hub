package com.alura.foro_hub.controller;

import com.alura.foro_hub.dto.TopicoRequest;
import com.alura.foro_hub.dto.TopicoResponse;
import com.alura.foro_hub.mapper.ITopicoEntityMapper;
import com.alura.foro_hub.model.CustomPage;
import com.alura.foro_hub.model.Topico;
import com.alura.foro_hub.service.TopicoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/topico")
@RequiredArgsConstructor
public class TopicoController {
    private final TopicoService topicoService;
    private final ITopicoEntityMapper topicoEntityMapper;

    @PostMapping
    public ResponseEntity<TopicoResponse> addTopico(@Valid @RequestBody TopicoRequest request, HttpServletRequest HTTPRequest) {
        Topico topico = topicoEntityMapper.topicoRequestToTopico(request);
        Topico topicoSaved = topicoService.saveTopico(topico, HTTPRequest);
        TopicoResponse response = topicoEntityMapper.toTopicoResponse(topicoSaved);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CustomPage<TopicoResponse>> getAllTopicos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        boolean ascending = "asc".equalsIgnoreCase(sortOrder);
        CustomPage<Topico> pageBrands = topicoService.getAllTopicos(page, size, ascending);
        CustomPage<TopicoResponse> responsePage = topicoEntityMapper.toPageOfTopicoResponse(pageBrands);

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> getTopico(@PathVariable Long id) {
        Topico topico = topicoService.getTopico(id);
        TopicoResponse response = topicoEntityMapper.toTopicoResponse(topico);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> updateTopico(@PathVariable Long id, @Valid @RequestBody TopicoRequest request) {
        Topico topico = topicoService.getTopico(id);
        Topico topicoRequest = topicoEntityMapper.topicoRequestToTopico(request);
        Topico topicoUpdated = topicoService.updateTopico(topico, topicoRequest);
        TopicoResponse response = topicoEntityMapper.toTopicoResponse(topicoUpdated);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        topicoService.deleteTopico(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
