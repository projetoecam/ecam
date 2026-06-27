package com.ecam.ecam.login.controller;

import com.ecam.ecam.login.dto.LoginRequestDTO;
import com.ecam.ecam.login.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> fazerLogin(@RequestBody LoginRequestDTO loginRequest) {
        try {
            String token = usuarioService.autenticarUsuario(loginRequest.login(), loginRequest.senha());
            
            Map<String, String> resposta = new HashMap<>();
            resposta.put("token", token);
            
            return ResponseEntity.ok(resposta);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}