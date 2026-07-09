package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.AtendimentoDTO;
import com.ecam.ecam.Cadastros.services.AtendimentoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
@RequiredArgsConstructor
public class AtendimentoController {


    private final AtendimentoService service;

    @GetMapping
    public ResponseEntity<List<AtendimentoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Endpoint extra para buscar o histórico de uma pessoa
    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<List<AtendimentoDTO>> listarPorPessoa(@PathVariable Integer idPessoa) {
        return ResponseEntity.ok(service.listarPorPessoa(idPessoa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AtendimentoDTO> salvar(@RequestBody AtendimentoDTO dto) {
        AtendimentoDTO novoAtendimento = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAtendimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoDTO> atualizar(@PathVariable Integer id, @RequestBody AtendimentoDTO dto) {
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