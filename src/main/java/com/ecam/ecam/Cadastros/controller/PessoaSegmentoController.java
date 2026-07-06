package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.PessoaSegmentoDTO;
import com.ecam.ecam.Cadastros.services.PessoaSegmentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa-segmentos")
public class PessoaSegmentoController {

    @Autowired
    private PessoaSegmentoService service;

    @GetMapping
    public ResponseEntity<List<PessoaSegmentoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
    
    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<List<PessoaSegmentoDTO>> listarPorPessoa(@PathVariable Integer idPessoa) {
        return ResponseEntity.ok(service.listarPorPessoa(idPessoa));
    }

    @PostMapping
    public ResponseEntity<PessoaSegmentoDTO> vincular(@RequestBody PessoaSegmentoDTO dto) {
        PessoaSegmentoDTO novoVinculo = service.vincular(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVinculo);
    }

    @DeleteMapping("/{idPessoa}/{idSegmento}")
    public ResponseEntity<Void> desvincular(@PathVariable Integer idPessoa, @PathVariable Integer idSegmento) {
        service.desvincular(idPessoa, idSegmento);
        return ResponseEntity.noContent().build();
    }
}