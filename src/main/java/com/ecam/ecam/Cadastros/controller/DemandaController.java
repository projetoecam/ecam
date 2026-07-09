package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.DemandaDTO;
import com.ecam.ecam.Cadastros.services.DemandaService;

import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandas")
@RequiredArgsConstructor
public class DemandaController {

  
    private final DemandaService service;

    @GetMapping
    public ResponseEntity<List<DemandaDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandaDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DemandaDTO> salvar(@RequestBody DemandaDTO dto) {
        DemandaDTO novaDemanda = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDemanda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandaDTO> atualizar(@PathVariable Integer id, @RequestBody DemandaDTO dto) {
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