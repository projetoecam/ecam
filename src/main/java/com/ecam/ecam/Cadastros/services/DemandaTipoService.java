package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.DemandaTipoDTO;
import com.ecam.ecam.Cadastros.model.Demanda;
import com.ecam.ecam.Cadastros.model.DemandaTipo;
import com.ecam.ecam.Cadastros.repository.DemandaTipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemandaTipoService {

    @Autowired
    private DemandaTipoRepository repository;

    public List<DemandaTipoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<DemandaTipoDTO> listarPorDemanda(Integer idDemanda) {
        return repository.findByDemandaId(idDemanda).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public DemandaTipoDTO buscarPorId(Integer id) {
        DemandaTipo entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de Demanda não encontrado!"));
        return converterParaDTO(entidade);
    }

    public DemandaTipoDTO salvar(DemandaTipoDTO dto) {
        DemandaTipo entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public DemandaTipoDTO atualizar(Integer id, DemandaTipoDTO dto) {
        DemandaTipo entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de Demanda não encontrado!"));

        entidadeExistente.setTipoSaude(dto.getTipoSaude());
        entidadeExistente.setDescricaoTipoSaude(dto.getDescricaoTipoSaude());
        entidadeExistente.setTipoInfraestrutura(dto.getTipoInfraestrutura());
        entidadeExistente.setDescricaoTipoInfraestrutura(dto.getDescricaoTipoInfraestrutura());
        entidadeExistente.setTipoEducacao(dto.getTipoEducacao());
        entidadeExistente.setDescricaoTipoEducacao(dto.getDescricaoTipoEducacao());
        entidadeExistente.setTipoSeguranca(dto.getTipoSeguranca());
        entidadeExistente.setDescricaoTipoSeguranca(dto.getDescricaoTipoSeguranca());
        entidadeExistente.setTipoOutros(dto.getTipoOutros());
        entidadeExistente.setDescricaoTipoOutros(dto.getDescricaoTipoOutros());

        if (dto.getIdDemanda() != null) {
            Demanda demanda = new Demanda();
            demanda.setId(dto.getIdDemanda());
            entidadeExistente.setDemanda(demanda);
        }

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    // --- Métodos de Conversão ---

    private DemandaTipoDTO converterParaDTO(DemandaTipo entidade) {
        return DemandaTipoDTO.builder()
                .id(entidade.getId())
                .idDemanda(entidade.getDemanda() != null ? entidade.getDemanda().getId() : null)
                .tipoSaude(entidade.getTipoSaude())
                .descricaoTipoSaude(entidade.getDescricaoTipoSaude())
                .tipoInfraestrutura(entidade.getTipoInfraestrutura())
                .descricaoTipoInfraestrutura(entidade.getDescricaoTipoInfraestrutura())
                .tipoEducacao(entidade.getTipoEducacao())
                .descricaoTipoEducacao(entidade.getDescricaoTipoEducacao())
                .tipoSeguranca(entidade.getTipoSeguranca())
                .descricaoTipoSeguranca(entidade.getDescricaoTipoSeguranca())
                .tipoOutros(entidade.getTipoOutros())
                .descricaoTipoOutros(entidade.getDescricaoTipoOutros())
                .build();
    }

    private DemandaTipo converterParaEntidade(DemandaTipoDTO dto) {
        DemandaTipo entidade = DemandaTipo.builder()
                .id(dto.getId())
                .tipoSaude(dto.getTipoSaude())
                .descricaoTipoSaude(dto.getDescricaoTipoSaude())
                .tipoInfraestrutura(dto.getTipoInfraestrutura())
                .descricaoTipoInfraestrutura(dto.getDescricaoTipoInfraestrutura())
                .tipoEducacao(dto.getTipoEducacao())
                .descricaoTipoEducacao(dto.getDescricaoTipoEducacao())
                .tipoSeguranca(dto.getTipoSeguranca())
                .descricaoTipoSeguranca(dto.getDescricaoTipoSeguranca())
                .tipoOutros(dto.getTipoOutros())
                .descricaoTipoOutros(dto.getDescricaoTipoOutros())
                .build();

        if (dto.getIdDemanda() != null) {
            Demanda demanda = new Demanda();
            demanda.setId(dto.getIdDemanda());
            entidade.setDemanda(demanda);
        }

        return entidade;
    }
}