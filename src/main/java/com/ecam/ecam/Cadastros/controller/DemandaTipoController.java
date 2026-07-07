package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.DemandaTipoDTO;
import com.ecam.ecam.Cadastros.services.DemandaTipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demanda-tipos")
public class DemandaTipoController {

    @Autowired
    private DemandaTipoService service;

    @GetMapping
    public ResponseEntity<List<DemandaTipoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Endpoint extra para buscar os detalhes de tipo de uma demanda
    @GetMapping("/demanda/{idDemanda}")
    public ResponseEntity<List<DemandaTipoDTO>> listarPorDemanda(@PathVariable Integer idDemanda) {
        return ResponseEntity.ok(service.listarPorDemanda(idDemanda));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandaTipoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DemandaTipoDTO> salvar(@RequestBody DemandaTipoDTO dto) {
        DemandaTipoDTO novoDemandaTipo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDemandaTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandaTipoDTO> atualizar(@PathVariable Integer id, @RequestBody DemandaTipoDTO dto) {
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