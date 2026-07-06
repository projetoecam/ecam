package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.ReuniaoDTO;
import com.ecam.ecam.Cadastros.services.ReuniaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reunioes")
public class ReuniaoController {

    @Autowired
    private ReuniaoService service;

    @GetMapping
    public ResponseEntity<List<ReuniaoDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    // Endpoint extra para buscar o histórico de reuniões em uma comunidade
    @GetMapping("/comunidade/{idComunidade}")
    public ResponseEntity<List<ReuniaoDTO>> listarPorComunidade(@PathVariable Integer idComunidade) {
        return ResponseEntity.ok(service.listarPorComunidade(idComunidade));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReuniaoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ReuniaoDTO> salvar(@RequestBody ReuniaoDTO dto) {
        ReuniaoDTO novaReuniao = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReuniao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReuniaoDTO> atualizar(@PathVariable Integer id, @RequestBody ReuniaoDTO dto) {
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