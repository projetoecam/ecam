package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.MacroRegiaoDTO;
import com.ecam.ecam.Cadastros.model.MacroRegiao;
import com.ecam.ecam.Cadastros.model.Municipio;
import com.ecam.ecam.Cadastros.repository.MacroRegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MacroRegiaoService {

    @Autowired
    private MacroRegiaoRepository repository;

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

        entidadeExistente.setNome(dto.getNome());
        entidadeExistente.setRegiao_apelido(dto.getRegiaoApelido());
        
        // Atualiza a referência do Município, se o ID for fornecido
        if (dto.getIdMunicipio() != null) {
            Municipio municipio = new Municipio();
            municipio.setId(dto.getIdMunicipio());
            entidadeExistente.setMunicipio(municipio);
        }

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    // --- Métodos de Conversão ---

    private MacroRegiaoDTO converterParaDTO(MacroRegiao entidade) {
        return MacroRegiaoDTO.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .regiaoApelido(entidade.getRegiao_apelido())
                // Pega o ID do município apenas se a entidade município não for nula
                .idMunicipio(entidade.getMunicipio() != null ? entidade.getMunicipio().getId() : null)
                .build();
    }

    private MacroRegiao converterParaEntidade(MacroRegiaoDTO dto) {
        MacroRegiao entidade = MacroRegiao.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .regiao_apelido(dto.getRegiaoApelido())
                .build();

        // Cria uma referência do Município usando apenas o ID
        if (dto.getIdMunicipio() != null) {
            Municipio municipio = new Municipio();
            municipio.setId(dto.getIdMunicipio());
            entidade.setMunicipio(municipio);
        }
        
        return entidade;
    }
}