package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.BairroDTO;
import com.ecam.ecam.Cadastros.services.BairroService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bairros")
@RequiredArgsConstructor
public class BairroController {


    private final BairroService service;

    @GetMapping
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<BairroDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<BairroDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CADASTRAR')")
    public ResponseEntity<BairroDTO> salvar(@RequestBody BairroDTO dto) {
        BairroDTO novoBairro = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoBairro);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR')")
    public ResponseEntity<BairroDTO> atualizar(@PathVariable Integer id, @RequestBody BairroDTO dto) {
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