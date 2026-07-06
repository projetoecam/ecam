package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.LiderancaDTO;
import com.ecam.ecam.Cadastros.services.LiderancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/liderancas")
public class LiderancaController {

    @Autowired
    private LiderancaService service;

    @GetMapping
    public ResponseEntity<List<LiderancaDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LiderancaDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LiderancaDTO> salvar(@RequestBody LiderancaDTO dto) {
        try {
            LiderancaDTO novaLideranca = service.salvar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaLideranca);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LiderancaDTO> atualizar(@PathVariable Integer id, @RequestBody LiderancaDTO dto) {
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