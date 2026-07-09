package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.AtendimentoDTO;
import com.ecam.ecam.Cadastros.model.Atendimento;
import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.login.model.Usuario;

import lombok.RequiredArgsConstructor;

import com.ecam.ecam.Cadastros.repository.AtendimentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtendimentoService {


    private final AtendimentoRepository repository;

    public List<AtendimentoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<AtendimentoDTO> listarPorPessoa(Integer idPessoa) {
        return repository.findByPessoaId(idPessoa).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public AtendimentoDTO buscarPorId(Integer id) {
        Atendimento entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado!"));
        return converterParaDTO(entidade);
    }

    public AtendimentoDTO salvar(AtendimentoDTO dto) {
        Atendimento entidade = converterParaEntidade(dto);
        
        // Se a data/hora do atendimento não for enviada, assume o momento do cadastro
        if (entidade.getDataHora() == null) {
            entidade.setDataHora(LocalDateTime.now());
        }

        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public AtendimentoDTO atualizar(Integer id, AtendimentoDTO dto) {
        Atendimento entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado!"));

        entidadeExistente.setDataHora(dto.dataHora());
        entidadeExistente.setMotivoContato(dto.motivoContato());
        entidadeExistente.setResultadoContato(dto.resultadoContato());
        entidadeExistente.setNecessitaRetorno(dto.necessitaRetorno());
        entidadeExistente.setDataProximoRetorno(dto.dataProximoRetorno());
        entidadeExistente.setObservacoes(dto.observacoes());

        if (dto.idPessoa() != null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(dto.idPessoa());
            entidadeExistente.setPessoa(pessoa);
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

    // --- Métodos de Conversão ---

    private AtendimentoDTO converterParaDTO(Atendimento entidade) {
        return AtendimentoDTO.builder()
                .id(entidade.getId())
                .idPessoa(entidade.getPessoa() != null ? entidade.getPessoa().getId() : null)
                .idUsuarioCadastro(entidade.getUsuarioCadastro() != null ? entidade.getUsuarioCadastro().getId() : null)
                .dataHora(entidade.getDataHora())
                .motivoContato(entidade.getMotivoContato())
                .resultadoContato(entidade.getResultadoContato())
                .necessitaRetorno(entidade.getNecessitaRetorno())
                .dataProximoRetorno(entidade.getDataProximoRetorno())
                .observacoes(entidade.getObservacoes())
                .build();
    }

    private Atendimento converterParaEntidade(AtendimentoDTO dto) {
        Atendimento entidade = Atendimento.builder()
                .id(dto.id())
                .dataHora(dto.dataHora())
                .motivoContato(dto.motivoContato())
                .resultadoContato(dto.resultadoContato())
                .necessitaRetorno(dto.necessitaRetorno())
                .dataProximoRetorno(dto.dataProximoRetorno())
                .observacoes(dto.observacoes())
                .build();

        if (dto.idPessoa() != null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(dto.idPessoa());
            entidade.setPessoa(pessoa);
        }

        if (dto.idUsuarioCadastro() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.idUsuarioCadastro());
            entidade.setUsuarioCadastro(usuario);
        }

        return entidade;
    }
}