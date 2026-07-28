package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.EncaminhamentoDTO;
import com.ecam.ecam.Cadastros.services.EncaminhamentoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/encaminhamentos")
@RequiredArgsConstructor
public class EncaminhamentoController {

    
    private final EncaminhamentoService service;

    @GetMapping
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<EncaminhamentoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/demanda/{idDemanda}")
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<EncaminhamentoDTO>> listarPorDemanda(@PathVariable Integer idDemanda) {
        return ResponseEntity.ok(service.listarPorDemanda(idDemanda));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<EncaminhamentoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CADASTRAR')")
    public ResponseEntity<EncaminhamentoDTO> salvar(@RequestBody EncaminhamentoDTO dto) {
        EncaminhamentoDTO novoEncaminhamento = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEncaminhamento);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR')")
    public ResponseEntity<EncaminhamentoDTO> atualizar(@PathVariable Integer id, @RequestBody EncaminhamentoDTO dto) {
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