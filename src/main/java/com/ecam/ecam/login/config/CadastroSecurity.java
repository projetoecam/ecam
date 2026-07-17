package com.ecam.ecam.login.config;

import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.Cadastros.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("cadastroSecurity")
@RequiredArgsConstructor
public class CadastroSecurity {

    private final PessoaRepository pessoaRepository;

    public boolean podeExcluirCadastro(Integer pessoaId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return false;
        }

        boolean isAdministradorOuCoordenador = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMINISTRADOR")
                        || a.getAuthority().equals("ROLE_COORDENADOR_GERAL"));

        if (isAdministradorOuCoordenador) {
            return true;
        }
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(pessoaId);

        if (pessoaOpt.isEmpty()) {

            return true;
        }

        Pessoa pessoa = pessoaOpt.get();

        String statusAtual = pessoa.getStatus();
        boolean estaConcluido = statusAtual != null && statusAtual.trim().equalsIgnoreCase("CONCLUIDO");

        return !estaConcluido;
    }
}