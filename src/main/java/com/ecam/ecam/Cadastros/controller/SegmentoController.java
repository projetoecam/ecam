package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.SegmentoDTO;
import com.ecam.ecam.Cadastros.services.SegmentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/segmentos")
public class SegmentoController {

    @Autowired
    private SegmentoService service;

    @GetMapping
    public ResponseEntity<List<SegmentoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SegmentoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SegmentoDTO> salvar(@RequestBody SegmentoDTO dto) {
        SegmentoDTO novoSegmento = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSegmento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SegmentoDTO> atualizar(@PathVariable Integer id, @RequestBody SegmentoDTO dto) {
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