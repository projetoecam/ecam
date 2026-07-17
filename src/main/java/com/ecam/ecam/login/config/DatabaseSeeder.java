package com.ecam.ecam.login.config;

import com.ecam.ecam.login.model.PerfilAcesso;
import com.ecam.ecam.login.model.Permissao;
import com.ecam.ecam.login.model.Usuario;
import com.ecam.ecam.login.repository.PerfilAcessoRepository;
import com.ecam.ecam.login.repository.PermissaoRepository;
import com.ecam.ecam.login.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final PermissaoRepository permissaoRepository;
    private final PerfilAcessoRepository perfilAcessoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        Permissao lerDados = criarPermissaoSeNaoExistir("LER_DADOS", "Permite visualizar dados no sistema");
        Permissao cadastrar = criarPermissaoSeNaoExistir("CADASTRAR", "Permite cadastrar novos registros no sistema");
        Permissao editar = criarPermissaoSeNaoExistir("EDITAR", "Permite alterar registros existentes");
        Permissao deletar = criarPermissaoSeNaoExistir("DELETAR", "Permite excluir registros");
        Permissao exportar = criarPermissaoSeNaoExistir("EXPORTAR", "Permite exportar dados");
        Permissao copiar = criarPermissaoSeNaoExistir("COPIAR", "Permite copiar registros");
        Permissao aprovarAlteracao = criarPermissaoSeNaoExistir("APROVAR_ALTERACAO", "Permite aprovar solicitações de alterações");

        criarPerfilSeNaoExistir("Administrador", "Possui acesso irrestrito a todas as funcionalidades", 
                Arrays.asList(lerDados, cadastrar, editar, deletar, exportar, copiar, aprovarAlteracao));
                
        criarPerfilSeNaoExistir("Coordenador Geral", "Possui acesso irrestrito às operações base", 
                Arrays.asList(lerDados, cadastrar, editar, deletar, exportar, copiar, aprovarAlteracao));
                
        criarPerfilSeNaoExistir("Coordenador de Equipe", "Acesso para gestão de equipas operacionais", 
                Arrays.asList(lerDados, cadastrar, editar, deletar, aprovarAlteracao));
                
        criarPerfilSeNaoExistir("Operador de Cadastro", "Acesso restrito para introdução de dados", 
                Arrays.asList(lerDados, cadastrar)); 
                
        criarPerfilSeNaoExistir("Operador de Atendimento", "Acesso para conduzir os atendimentos", 
                Arrays.asList(lerDados, cadastrar, editar));
                
        criarPerfilSeNaoExistir("Jurídico", "Acesso de consulta e edição limitada para questões legais", 
                Arrays.asList(lerDados, cadastrar, editar));
                
        criarPerfilSeNaoExistir("Prestação de Contas", "Acesso focado na validação financeira e relatórios", 
                Arrays.asList(lerDados, cadastrar, editar));
                
        criarPerfilSeNaoExistir("Comunicação", "Acesso para elaboração de materiais e comunicados", 
                Arrays.asList(lerDados, cadastrar, editar));

        // Vincula o perfil administrador aos usuários que ficaram sem perfil na migração
        vincularPerfilAdminAoUsuario();
    }

    private Permissao criarPermissaoSeNaoExistir(String nome, String descricao) {
        Optional<Permissao> permissaoOpt = permissaoRepository.findByNome(nome);
        if (permissaoOpt.isEmpty()) {
            Permissao permissao = Permissao.builder()
                    .nome(nome)
                    .descricao(descricao)
                    .build();
            return permissaoRepository.save(permissao);
        }
        return permissaoOpt.get();
    }

    private void criarPerfilSeNaoExistir(String nome, String descricao, List<Permissao> permissoes) {
        Optional<PerfilAcesso> perfilOpt = perfilAcessoRepository.findByNome(nome);
        if (perfilOpt.isEmpty()) {
            PerfilAcesso perfil = PerfilAcesso.builder()
                    .nome(nome)
                    .descricao(descricao)
                    .permissoes(new HashSet<>(permissoes))
                    .build();
            perfilAcessoRepository.save(perfil);
        }
    }

    private void vincularPerfilAdminAoUsuario() {
        Optional<PerfilAcesso> adminPerfilOpt = perfilAcessoRepository.findByNome("Administrador");
        if (adminPerfilOpt.isPresent()) {
            PerfilAcesso adminPerfil = adminPerfilOpt.get();
            List<Usuario> usuarios = usuarioRepository.findAll();
            
            for (Usuario usuario : usuarios) {
                // Se o usuário antigo estiver com a lista de perfis vazia, injetamos o Administrador
                if (usuario.getPerfis() == null || usuario.getPerfis().isEmpty()) {
                    if (usuario.getPerfis() == null) {
                        usuario.setPerfis(new HashSet<>());
                    }
                    usuario.getPerfis().add(adminPerfil);
                    usuarioRepository.save(usuario);
                }
            }
        }
    }
}