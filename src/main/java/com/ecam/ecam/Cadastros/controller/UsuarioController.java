package com.ecam.ecam.Cadastros.controller;

import com.ecam.ecam.Cadastros.model.Usuario;
import com.ecam.ecam.Cadastros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Esta rota atende quando você digita http://localhost:8080/usuarios no navegador
    @GetMapping
    public String listarUsuarios(Model model) {
        // 1. Vai no SQL Server e busca todos os usuários
        List<Usuario> listaDeUsuarios = usuarioRepository.findAll();
        
        // 2. Envia essa lista para o HTML com o apelido "usuarios"
        model.addAttribute("usuarios", listaDeUsuarios);
        
        // 3. Retorna o nome exato do arquivo HTML (sem a extensão .html)
        return "usuarios-list"; 
    }
}