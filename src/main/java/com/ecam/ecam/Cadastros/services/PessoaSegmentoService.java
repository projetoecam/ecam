package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.PessoaSegmentoDTO;
import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.Cadastros.model.PessoaSegmento;
import com.ecam.ecam.Cadastros.model.PessoaSegmentoId;
import com.ecam.ecam.Cadastros.model.Segmento;
import com.ecam.ecam.Cadastros.repository.PessoaSegmentoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaSegmentoService {

    private final PessoaSegmentoRepository repository;

    public List<PessoaSegmentoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }
    
    public List<PessoaSegmentoDTO> listarPorPessoa(Integer idPessoa) {
        return repository.findByIdIdPessoa(idPessoa).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public PessoaSegmentoDTO vincular(PessoaSegmentoDTO dto) {
        PessoaSegmento entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public void desvincular(Integer idPessoa, Integer idSegmento) {
        PessoaSegmentoId idComposto = new PessoaSegmentoId(idPessoa, idSegmento);
        repository.deleteById(idComposto);
    }

    // --- Métodos de Conversão ---

    private PessoaSegmentoDTO converterParaDTO(PessoaSegmento entidade) {
        return PessoaSegmentoDTO.builder()
                .idPessoa(entidade.getId().getIdPessoa())
                .idSegmento(entidade.getId().getIdSegmento())
                .build();
    }

    private PessoaSegmento converterParaEntidade(PessoaSegmentoDTO dto) {
        PessoaSegmentoId idComposto = new PessoaSegmentoId(dto.idPessoa(), dto.idSegmento());
        
        Pessoa pessoa = new Pessoa();
        pessoa.setId(dto.idPessoa());

        Segmento segmento = new Segmento();
        segmento.setId(dto.idSegmento());

        return PessoaSegmento.builder()
                .id(idComposto)
                .pessoa(pessoa)
                .segmento(segmento)
                .build();
    }
}