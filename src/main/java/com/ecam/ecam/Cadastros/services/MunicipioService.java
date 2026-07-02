package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.MunicipioDTO;
import com.ecam.ecam.Cadastros.model.Municipio;
import com.ecam.ecam.Cadastros.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository repository;

    public List<MunicipioDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public MunicipioDTO buscarPorId(Integer id) {
        Municipio entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Município não encontrado!"));
        return converterParaDTO(entidade);
    }

    public MunicipioDTO salvar(MunicipioDTO dto) {
        Municipio entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public MunicipioDTO atualizar(Integer id, MunicipioDTO dto) {
        Municipio entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Município não encontrado!"));

        entidadeExistente.setNome(dto.getNome());
        entidadeExistente.setUf(dto.getUf());

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    // --- Métodos de Conversão ---

    private MunicipioDTO converterParaDTO(Municipio entidade) {
        return MunicipioDTO.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .uf(entidade.getUf())
                .build();
    }

    private Municipio converterParaEntidade(MunicipioDTO dto) {
        return Municipio.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .uf(dto.getUf())
                .build();
    }
}