package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.ReuniaoPresencaDTO;
import com.ecam.ecam.Cadastros.services.ReuniaoPresencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reuniao-presencas")
public class ReuniaoPresencaController {

    @Autowired
    private ReuniaoPresencaService service;

    @GetMapping
    public ResponseEntity<List<ReuniaoPresencaDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }
    
    @GetMapping("/reuniao/{idReuniao}")
    public ResponseEntity<List<ReuniaoPresencaDTO>> listarPorReuniao(@PathVariable Integer idReuniao) {
        return ResponseEntity.ok(service.listarPorReuniao(idReuniao));
    }

    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<List<ReuniaoPresencaDTO>> listarPorPessoa(@PathVariable Integer idPessoa) {
        return ResponseEntity.ok(service.listarPorPessoa(idPessoa));
    }

    @PostMapping
    public ResponseEntity<ReuniaoPresencaDTO> registrarPresenca(@RequestBody ReuniaoPresencaDTO dto) {
        ReuniaoPresencaDTO novaPresenca = service.registrarPresenca(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPresenca);
    }

    @PatchMapping("/{idReuniao}/{idPessoa}/assinatura")
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
    public ResponseEntity<Void> removerPresenca(@PathVariable Integer idReuniao, @PathVariable Integer idPessoa) {
        service.removerPresenca(idReuniao, idPessoa);
        return ResponseEntity.noContent().build();
    }
}