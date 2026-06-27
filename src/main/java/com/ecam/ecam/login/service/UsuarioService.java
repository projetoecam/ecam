package com.ecam.ecam.login.service;

import com.ecam.ecam.login.model.Usuario;
import com.ecam.ecam.login.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    public UsuarioService(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    public String autenticarUsuario(String login, String senhaRecebida) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.buscarPorLogin(login);

        if (usuarioEncontrado.isPresent()) {
            Usuario usuario = usuarioEncontrado.get();
            
            if (usuario.getSenha_hash().equals(senhaRecebida)) {
                String novoCodigoSessao = UUID.randomUUID().toString();
                usuario.setCodigo_sessao(novoCodigoSessao);
                usuarioRepository.save(usuario);
                
                return tokenService.gerarToken(usuario);
            } else {
                throw new RuntimeException("Senha incorreta!");
            }
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }
}