package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.MacroRegiaoDTO;
import com.ecam.ecam.Cadastros.services.MacroRegiaoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/macro-regioes")
@RequiredArgsConstructor
public class MacroRegiaoController {

    private final MacroRegiaoService service;

    @GetMapping
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<MacroRegiaoDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<MacroRegiaoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CADASTRAR')")
    public ResponseEntity<MacroRegiaoDTO> salvar(@RequestBody MacroRegiaoDTO dto) {
        MacroRegiaoDTO novaMacroRegiao = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMacroRegiao);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR')")
    public ResponseEntity<MacroRegiaoDTO> atualizar(@PathVariable Integer id, @RequestBody MacroRegiaoDTO dto) {
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