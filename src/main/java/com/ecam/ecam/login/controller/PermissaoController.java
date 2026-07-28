package com.ecam.ecam.login.controller;

import com.ecam.ecam.login.model.Permissao;
import com.ecam.ecam.login.repository.PermissaoRepository;
import com.ecam.ecam.login.repository.PerfilAcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/permissoes")
@RequiredArgsConstructor
public class PermissaoController {

    private final PermissaoRepository permissaoRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CADASTRAR')")
    public ResponseEntity<List<Permissao>> listarTodas() {
        return ResponseEntity.ok(permissaoRepository.findAll());
    }
}