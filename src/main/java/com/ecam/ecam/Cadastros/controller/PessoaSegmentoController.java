package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.PessoaSegmentoDTO;
import com.ecam.ecam.Cadastros.services.PessoaSegmentoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pessoa-segmentos")
public class PessoaSegmentoController {

  
    private final PessoaSegmentoService service;

    @GetMapping
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<PessoaSegmentoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
    
    @GetMapping("/pessoa/{idPessoa}")
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<PessoaSegmentoDTO>> listarPorPessoa(@PathVariable Integer idPessoa) {
        return ResponseEntity.ok(service.listarPorPessoa(idPessoa));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CADASTRAR')")
    public ResponseEntity<PessoaSegmentoDTO> vincular(@RequestBody PessoaSegmentoDTO dto) {
        PessoaSegmentoDTO novoVinculo = service.vincular(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVinculo);
    }

    @DeleteMapping("/{idPessoa}/{idSegmento}")
    @PreAuthorize("hasAuthority('EDITAR')")
    public ResponseEntity<Void> desvincular(@PathVariable Integer idPessoa, @PathVariable Integer idSegmento) {
        service.desvincular(idPessoa, idSegmento);
        return ResponseEntity.noContent().build();
    }
}