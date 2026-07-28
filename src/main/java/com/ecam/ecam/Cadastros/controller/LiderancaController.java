package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.LiderancaDTO;
import com.ecam.ecam.Cadastros.services.LiderancaService;

import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/liderancas")
@RequiredArgsConstructor
public class LiderancaController {


    private final LiderancaService service;

    @GetMapping
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<LiderancaDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<LiderancaDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CADASTRAR')")
    public ResponseEntity<LiderancaDTO> salvar(@RequestBody LiderancaDTO dto) {
        try {
            LiderancaDTO novaLideranca = service.salvar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaLideranca);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR')")
    public ResponseEntity<LiderancaDTO> atualizar(@PathVariable Integer id, @RequestBody LiderancaDTO dto) {
        try {
            return ResponseEntity.ok(service.atualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETAR')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}