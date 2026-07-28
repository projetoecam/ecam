package com.ecam.ecam.login.controller;

import com.ecam.ecam.login.model.PerfilAcesso;
import com.ecam.ecam.login.repository.PerfilAcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/perfis")
@RequiredArgsConstructor
public class PerfilAcessoController {

    private final PerfilAcessoRepository perfilAcessoRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('LER_DADOS')")
    public ResponseEntity<List<PerfilAcesso>> listarTodos() {
        List<PerfilAcesso> perfis = perfilAcessoRepository.findAll();
        return ResponseEntity.ok(perfis);
    }
}