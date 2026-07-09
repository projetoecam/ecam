package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.ComunidadeDTO;
import com.ecam.ecam.Cadastros.services.ComunidadeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comunidades")
@RequiredArgsConstructor
public class ComunidadeController {

    
    private final ComunidadeService service;

    @GetMapping
    public ResponseEntity<List<ComunidadeDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunidadeDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ComunidadeDTO> salvar(@RequestBody ComunidadeDTO dto) {
        ComunidadeDTO novaComunidade = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaComunidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComunidadeDTO> atualizar(@PathVariable Integer id, @RequestBody ComunidadeDTO dto) {
        try {
            return ResponseEntity.ok(service.atualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}