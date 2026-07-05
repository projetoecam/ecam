package com.ecam.ecam.login.service;

import com.ecam.ecam.login.dto.UsuarioDTO;
import com.ecam.ecam.login.model.Usuario;
import com.ecam.ecam.login.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public String autenticarUsuario(String login, String senhaRecebida) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.buscarPorLogin(login);
        if (usuarioEncontrado.isPresent()) {
            Usuario usuario = usuarioEncontrado.get();
            if (passwordEncoder.matches(senhaRecebida, usuario.getSenha_hash())) {
                String novoCodigoSessao = UUID.randomUUID().toString();
                usuario.setCodigo_sessao(novoCodigoSessao);
                usuarioRepository.save(usuario);
                return tokenService.gerarToken(usuario);
            }
            throw new RuntimeException("Senha incorreta!");
        }
        throw new RuntimeException("Usuário não encontrado!");
    }

    public void cadastrarUsuario(UsuarioDTO dto) {
        if (usuarioRepository.buscarPorLogin(dto.getLogin_usuario()).isPresent()) {
            throw new RuntimeException("Login já cadastrado!");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setLogin_usuario(dto.getLogin_usuario());
        novoUsuario.setSenha_hash(passwordEncoder.encode(dto.getSenha_hash()));
        novoUsuario.setPerfil(dto.getPerfil());
        novoUsuario.setAtivo(true);
        novoUsuario.setData_criacao(Date.valueOf(LocalDate.now()));
        
        usuarioRepository.save(novoUsuario);
    }

    public Usuario atualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        usuario.setNome(dto.getNome());
        usuario.setPerfil(dto.getPerfil());
        usuario.setAtivo(dto.isAtivo()); 
        if (dto.getSenha_hash() != null && !dto.getSenha_hash().trim().isEmpty()) {
            usuario.setSenha_hash(passwordEncoder.encode(dto.getSenha_hash()));
        }

        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}