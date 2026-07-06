package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.AtendimentoDTO;
import com.ecam.ecam.Cadastros.model.Atendimento;
import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.login.model.Usuario;
import com.ecam.ecam.Cadastros.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository repository;

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

        entidadeExistente.setDataHora(dto.getDataHora());
        entidadeExistente.setMotivoContato(dto.getMotivoContato());
        entidadeExistente.setResultadoContato(dto.getResultadoContato());
        entidadeExistente.setNecessitaRetorno(dto.getNecessitaRetorno());
        entidadeExistente.setDataProximoRetorno(dto.getDataProximoRetorno());
        entidadeExistente.setObservacoes(dto.getObservacoes());

        if (dto.getIdPessoa() != null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(dto.getIdPessoa());
            entidadeExistente.setPessoa(pessoa);
        }

        if (dto.getIdUsuarioCadastro() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.getIdUsuarioCadastro());
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
                .id(dto.getId())
                .dataHora(dto.getDataHora())
                .motivoContato(dto.getMotivoContato())
                .resultadoContato(dto.getResultadoContato())
                .necessitaRetorno(dto.getNecessitaRetorno())
                .dataProximoRetorno(dto.getDataProximoRetorno())
                .observacoes(dto.getObservacoes())
                .build();

        if (dto.getIdPessoa() != null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(dto.getIdPessoa());
            entidade.setPessoa(pessoa);
        }

        if (dto.getIdUsuarioCadastro() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.getIdUsuarioCadastro());
            entidade.setUsuarioCadastro(usuario);
        }

        return entidade;
    }
}