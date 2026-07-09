package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.DemandaTipoDTO;
import com.ecam.ecam.Cadastros.services.DemandaTipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/demanda-tipos")
public class DemandaTipoController {

    @Autowired
    private DemandaTipoService service;

    @GetMapping("/demanda/{id}")
    public ResponseEntity<List<DemandaTipoDTO>> listar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.listarPorDemanda(id));
    }

    @PostMapping
    public ResponseEntity<DemandaTipoDTO> salvar(@RequestBody DemandaTipoDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandaTipoDTO> atualizar(@PathVariable Integer id, @RequestBody DemandaTipoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }
}