package com.ecam.ecam.login.service;

import com.ecam.ecam.login.dto.UsuarioDTO;
import com.ecam.ecam.login.model.PerfilAcesso;
import com.ecam.ecam.login.model.Usuario;
import com.ecam.ecam.login.repository.PerfilAcessoRepository;
import com.ecam.ecam.login.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final PerfilAcessoRepository perfilAcessoRepository;

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
        // Cláusulas de Guarda: Bloqueiam a execução imediatamente se dados essenciais faltarem
        if (dto.getPerfil() == null || dto.getPerfil().trim().isEmpty()) {
            throw new RuntimeException("O perfil de acesso é obrigatório para o cadastro.");
        }
        if (dto.getLogin_usuario() == null || dto.getLogin_usuario().trim().isEmpty()) {
            throw new RuntimeException("O login é obrigatório.");
        }

        // Validação de unicidade no banco de dados
        if (usuarioRepository.buscarPorLogin(dto.getLogin_usuario()).isPresent()) {
            throw new RuntimeException("Login já cadastrado!");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setLogin_usuario(dto.getLogin_usuario());
        novoUsuario.setSenha_hash(passwordEncoder.encode(dto.getSenha_hash()));
        novoUsuario.setAtivo(true);
        novoUsuario.setData_criacao(Date.valueOf(LocalDate.now()));
        
        // Etapa Crítica de Associação: 
        // Como já garantimos acima que o perfil não é nulo, esta procura será segura.
        PerfilAcesso perfil = perfilAcessoRepository.findByNome(dto.getPerfil())
                .orElseThrow(() -> new RuntimeException("Perfil de acesso inválido ou não encontrado: " + dto.getPerfil()));
        
        novoUsuario.getPerfis().add(perfil);
        
        usuarioRepository.save(novoUsuario);
    }

    public Usuario atualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) {
            usuario.setNome(dto.getNome());
        }
        
        if (dto.getAtivo() != null) {
            usuario.setAtivo(dto.getAtivo()); 
        }
        
        if (dto.getSenha_hash() != null && !dto.getSenha_hash().trim().isEmpty()) {
            usuario.setSenha_hash(passwordEncoder.encode(dto.getSenha_hash()));
        }

        // Flexibilidade na Atualização:
        // O utilizador só terá o seu perfil alterado se o frontend enviar explicitamente este dado no JSON.
        // Se vier nulo, a aplicação ignora e preserva os acessos antigos.
        if (dto.getPerfil() != null && !dto.getPerfil().trim().isEmpty()) {
            PerfilAcesso perfil = perfilAcessoRepository.findByNome(dto.getPerfil())
                    .orElseThrow(() -> new RuntimeException("Perfil de acesso inválido ou não encontrado: " + dto.getPerfil()));
            
            // Limpa as permissões antigas e injeta a nova para que o utilizador não acumule privilégios incorretamente
            usuario.getPerfis().clear();
            usuario.getPerfis().add(perfil);
        }

        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}