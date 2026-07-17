package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.ReuniaoDTO;
import com.ecam.ecam.Cadastros.services.ReuniaoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reunioes")
@RequiredArgsConstructor
public class ReuniaoController {

    private final ReuniaoService service;

    @GetMapping
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<ReuniaoDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/comunidade/{idComunidade}")
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<ReuniaoDTO>> listarPorComunidade(@PathVariable Integer idComunidade) {
        return ResponseEntity.ok(service.listarPorComunidade(idComunidade));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<ReuniaoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CADASTRAR')")
    public ResponseEntity<ReuniaoDTO> salvar(@RequestBody ReuniaoDTO dto) {
        ReuniaoDTO novaReuniao = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReuniao);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR')")
    public ResponseEntity<ReuniaoDTO> atualizar(@PathVariable Integer id, @RequestBody ReuniaoDTO dto) {
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