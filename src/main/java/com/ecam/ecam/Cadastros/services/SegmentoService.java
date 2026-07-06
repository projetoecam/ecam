package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.SegmentoDTO;
import com.ecam.ecam.Cadastros.model.Segmento;
import com.ecam.ecam.Cadastros.repository.SegmentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SegmentoService {

    @Autowired
    private SegmentoRepository repository;

    public List<SegmentoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public SegmentoDTO buscarPorId(Integer id) {
        Segmento entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Segmento não encontrado!"));
        return converterParaDTO(entidade);
    }

    public SegmentoDTO salvar(SegmentoDTO dto) {
        Segmento entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public SegmentoDTO atualizar(Integer id, SegmentoDTO dto) {
        Segmento entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Segmento não encontrado!"));

        entidadeExistente.setNome(dto.getNome());

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    // --- Métodos de Conversão ---

    private SegmentoDTO converterParaDTO(Segmento entidade) {
        return SegmentoDTO.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .build();
    }

    private Segmento converterParaEntidade(SegmentoDTO dto) {
        return Segmento.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .build();
    }
}