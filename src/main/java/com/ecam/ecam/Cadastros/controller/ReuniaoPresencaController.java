package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.ReuniaoPresencaDTO;
import com.ecam.ecam.Cadastros.services.ReuniaoPresencaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reuniao-presencas")
@RequiredArgsConstructor
public class ReuniaoPresencaController {


    private final ReuniaoPresencaService service;

    @GetMapping
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<ReuniaoPresencaDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }
    
    @GetMapping("/reuniao/{idReuniao}")
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<ReuniaoPresencaDTO>> listarPorReuniao(@PathVariable Integer idReuniao) {
        return ResponseEntity.ok(service.listarPorReuniao(idReuniao));
    }

    @GetMapping("/pessoa/{idPessoa}")
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<ReuniaoPresencaDTO>> listarPorPessoa(@PathVariable Integer idPessoa) {
        return ResponseEntity.ok(service.listarPorPessoa(idPessoa));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CADASTRAR')")
    public ResponseEntity<ReuniaoPresencaDTO> registrarPresenca(@RequestBody ReuniaoPresencaDTO dto) {
        ReuniaoPresencaDTO novaPresenca = service.registrarPresenca(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPresenca);
    }

    @PatchMapping("/{idReuniao}/{idPessoa}/assinatura")
    @PreAuthorize("hasAuthority('EDITAR')")
    public ResponseEntity<ReuniaoPresencaDTO> atualizarAssinatura(
            @PathVariable Integer idReuniao, 
            @PathVariable Integer idPessoa, 
            @RequestParam Boolean confirmada) {
        try {
            return ResponseEntity.ok(service.atualizarAssinatura(idReuniao, idPessoa, confirmada));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idReuniao}/{idPessoa}")
    @PreAuthorize("hasAuthority('DELETAR')")
    public ResponseEntity<Void> removerPresenca(@PathVariable Integer idReuniao, @PathVariable Integer idPessoa) {
        service.removerPresenca(idReuniao, idPessoa);
        return ResponseEntity.noContent().build();
    }
}