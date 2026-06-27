package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.MunicipioRequest;
import com.ecam.ecam.Cadastros.dto.MunicipioResponse;
import com.ecam.ecam.Cadastros.services.MunicipioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/municipios")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService service;

    @PostMapping
    public ResponseEntity<MunicipioResponse> criar(@RequestBody @Valid MunicipioRequest request) {
        MunicipioResponse response = service.criar(request);
        
        // Padrão REST: Retornar 201 Created com a URI do novo recurso no Header Location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
                
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MunicipioResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<MunicipioResponse>> listarTodos(Pageable pageable) {
        // Exemplo de uso: /api/v1/municipios?page=0&size=10&sort=nome,asc
        return ResponseEntity.ok(service.listarTodos(pageable));
    }
}