package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.PessoaDTO;
import com.ecam.ecam.Cadastros.model.Comunidade;
import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.login.model.Usuario;
import com.ecam.ecam.Cadastros.repository.PessoaRepository;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    
    private final PessoaRepository repository;


    public List<PessoaDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public PessoaDTO buscarPorId(Integer id) {
        Pessoa entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));
        return converterParaDTO(entidade);
    }

    @Transactional
    public PessoaDTO salvar(PessoaDTO dto) {
        Pessoa entidade = converterParaEntidade(dto);

        if (entidade.getTituloEleitor() != null &&
                (entidade.getTituloEleitor().equals("NAO_INFORMADO") || entidade.getTituloEleitor().isEmpty())) {
            entidade.setTituloEleitor(null);
        }

        if (entidade.getDataCadastro() == null) {
            entidade.setDataCadastro(LocalDateTime.now());
        }

        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    @Transactional
    public PessoaDTO atualizar(Integer id, PessoaDTO dto) {
        Pessoa entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));

        entidadeExistente.setNomeCompleto(dto.nomeCompleto());
        entidadeExistente.setCpf(dto.cpf());
        entidadeExistente.setTituloEleitor(dto.tituloEleitor());
        entidadeExistente.setNomeMae(dto.nomeMae());
        entidadeExistente.setDataNascimento(dto.dataNascimento());
        entidadeExistente.setTelefone(dto.telefone());
        entidadeExistente.setWhatsapp(dto.whatsapp());
        entidadeExistente.setEnderecoCompleto(dto.enderecoCompleto());
        entidadeExistente.setCep(dto.cep());
        entidadeExistente.setOrigemCadastro(dto.origemCadastro());
        entidadeExistente.setStatus(dto.status());
        entidadeExistente.setObservacoes(dto.observacoes());

        if (dto.idComunidade() != null) {
            Comunidade comunidade = new Comunidade();
            comunidade.setId(dto.idComunidade());
            entidadeExistente.setComunidade(comunidade);
        }

        if (dto.idLiderResponsavel() != null) {
            Pessoa lider = new Pessoa();
            lider.setId(dto.idLiderResponsavel());
            entidadeExistente.setLiderResponsavel(lider);
        } else {
            entidadeExistente.setLiderResponsavel(null);
        }

        if (dto.idLiderRegional() != null) {
            Pessoa liderRegional = new Pessoa();
            liderRegional.setId(dto.idLiderRegional());
            entidadeExistente.setLiderRegional(liderRegional);
        } else {
            entidadeExistente.setLiderRegional(null);
        }

        if (dto.idUsuarioCadastro() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.idUsuarioCadastro());
            entidadeExistente.setUsuarioCadastro(usuario);
        }

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    private PessoaDTO converterParaDTO(Pessoa entidade) {
        return PessoaDTO.builder()
                .id(entidade.getId())
                .nomeCompleto(entidade.getNomeCompleto())
                .cpf(entidade.getCpf())
                .tituloEleitor(entidade.getTituloEleitor())
                .nomeMae(entidade.getNomeMae())
                .dataNascimento(entidade.getDataNascimento())
                .telefone(entidade.getTelefone())
                .whatsapp(entidade.getWhatsapp())
                .idComunidade(entidade.getComunidade() != null ? entidade.getComunidade().getId() : null)
                .enderecoCompleto(entidade.getEnderecoCompleto())
                .cep(entidade.getCep())
                .origemCadastro(entidade.getOrigemCadastro())
                .idLiderResponsavel(
                        entidade.getLiderResponsavel() != null ? entidade.getLiderResponsavel().getId() : null)
                .idLiderRegional(entidade.getLiderRegional() != null ? entidade.getLiderRegional().getId() : null)
                .status(entidade.getStatus())
                .observacoes(entidade.getObservacoes())
                .idUsuarioCadastro(entidade.getUsuarioCadastro() != null ? entidade.getUsuarioCadastro().getId() : null)
                .dataCadastro(entidade.getDataCadastro())
                .build();
    }

    private Pessoa converterParaEntidade(PessoaDTO dto) {
        Pessoa entidade = Pessoa.builder()
                .id(dto.id())
                .nomeCompleto(dto.nomeCompleto())
                .cpf(dto.cpf())
                .tituloEleitor(dto.tituloEleitor())
                .nomeMae(dto.nomeMae())
                .dataNascimento(dto.dataNascimento())
                .telefone(dto.telefone())
                .whatsapp(dto.whatsapp())
                .enderecoCompleto(dto.enderecoCompleto())
                .cep(dto.cep())
                .origemCadastro(dto.origemCadastro())
                .status(dto.status())
                .observacoes(dto.observacoes())
                .dataCadastro(dto.dataCadastro())
                .build();

        if (dto.idComunidade() != null) {
            Comunidade comunidade = new Comunidade();
            comunidade.setId(dto.idComunidade());
            entidade.setComunidade(comunidade);
        }

        if (dto.idLiderResponsavel() != null) {
            Pessoa lider = new Pessoa();
            lider.setId(dto.idLiderResponsavel());
            entidade.setLiderResponsavel(lider);
        }

        if (dto.idLiderRegional() != null) {
            Pessoa liderRegional = new Pessoa();
            liderRegional.setId(dto.idLiderRegional());
            entidade.setLiderRegional(liderRegional);
        }

        if (dto.idUsuarioCadastro() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.idUsuarioCadastro());
            entidade.setUsuarioCadastro(usuario);
        }

        return entidade;
    }
}