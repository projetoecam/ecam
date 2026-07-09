package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.MacroRegiaoDTO;
import com.ecam.ecam.Cadastros.model.MacroRegiao;
import com.ecam.ecam.Cadastros.model.Municipio;
import com.ecam.ecam.Cadastros.repository.MacroRegiaoRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MacroRegiaoService {


    private final MacroRegiaoRepository repository;

    public List<MacroRegiaoDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public MacroRegiaoDTO buscarPorId(Integer id) {
        MacroRegiao entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Macro Região não encontrada!"));
        return converterParaDTO(entidade);
    }

    public MacroRegiaoDTO salvar(MacroRegiaoDTO dto) {
        MacroRegiao entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public MacroRegiaoDTO atualizar(Integer id, MacroRegiaoDTO dto) {
        MacroRegiao entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Macro Região não encontrada!"));

        entidadeExistente.setNome(dto.nome());
        entidadeExistente.setRegiao_apelido(dto.regiaoApelido());
        
        if (dto.idMunicipio() != null) {
            Municipio municipio = new Municipio();
            municipio.setId(dto.idMunicipio());
            entidadeExistente.setMunicipio(municipio);
        }

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    private MacroRegiaoDTO converterParaDTO(MacroRegiao entidade) {
        return MacroRegiaoDTO.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .regiaoApelido(entidade.getRegiao_apelido())
                .idMunicipio(entidade.getMunicipio() != null ? entidade.getMunicipio().getId() : null)
                .nomeMunicipio(entidade.getMunicipio() != null ? entidade.getMunicipio().getNome() : null) 
                .build();
    }

    private MacroRegiao converterParaEntidade(MacroRegiaoDTO dto) {
        MacroRegiao entidade = MacroRegiao.builder()
                .id(dto.id())
                .nome(dto.nome())
                .regiao_apelido(dto.regiaoApelido())
                .build();

        if (dto.idMunicipio() != null) {
            Municipio municipio = new Municipio();
            municipio.setId(dto.idMunicipio());
            entidade.setMunicipio(municipio);
        }
        
        return entidade;
    }
}