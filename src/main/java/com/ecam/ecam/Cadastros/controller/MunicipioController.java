package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.dto.MunicipioDTO;
import com.ecam.ecam.Cadastros.services.MunicipioService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/municipios")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService service;

    @GetMapping
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<MunicipioDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<MunicipioDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CADASTRAR')")
    public ResponseEntity<MunicipioDTO> salvar(@RequestBody MunicipioDTO dto) {
        MunicipioDTO novoMunicipio = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMunicipio);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR')")
    public ResponseEntity<MunicipioDTO> atuaatualizar(@PathVariable Integer id, @RequestBody MunicipioDTO dto) {
        try {
            return ResponseEntity.ok(service.atualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id) {
        service.deletar(id);
        //return ResponseEntity.noContent().build(); 204
        return ResponseEntity.ok("Município excluído com sucesso!"); //200
    }
}