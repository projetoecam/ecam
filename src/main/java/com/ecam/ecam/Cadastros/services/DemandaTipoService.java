package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.DemandaTipoDTO;
import com.ecam.ecam.Cadastros.model.Demanda;
import com.ecam.ecam.Cadastros.model.DemandaTipo;
import com.ecam.ecam.Cadastros.repository.DemandaTipoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelas regras de negócio da entidade DemandaTipo.
 */
@Service
@RequiredArgsConstructor
public class DemandaTipoService {

    private final DemandaTipoRepository repository;
    
    public List<DemandaTipoDTO> listarPorDemanda(Integer idDemanda) {
        return repository.findByDemandaId(idDemanda).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DemandaTipoDTO salvar(DemandaTipoDTO dto) {
        DemandaTipo entidade = converterParaEntidade(dto);
        return converterParaDTO(repository.save(entidade));
    }

    @Transactional
    public DemandaTipoDTO atualizar(Integer id, DemandaTipoDTO dto) {
        DemandaTipo entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de Demanda não encontrado!"));
        
        entidadeExistente.setTipoSaude(dto.tipoSaude());
        entidadeExistente.setDescricaoTipoSaude(dto.descricaoTipoSaude());
        
        entidadeExistente.setTipoInfraestrutura(dto.tipoInfraestrutura());
        entidadeExistente.setDescricaoTipoInfraestrutura(dto.descricaoTipoInfraestrutura());
        
        entidadeExistente.setTipoEducacao(dto.tipoEducacao());
        entidadeExistente.setDescricaoTipoEducacao(dto.descricaoTipoEducacao());
        
        entidadeExistente.setTipoSeguranca(dto.tipoSeguranca());
        entidadeExistente.setDescricaoTipoSeguranca(dto.descricaoTipoSeguranca());
        
        entidadeExistente.setTipoOutros(dto.tipoOutros());
        entidadeExistente.setDescricaoTipoOutros(dto.descricaoTipoOutros());
        
        return converterParaDTO(repository.save(entidadeExistente));
    }

    @Transactional
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
        Demanda demanda = new Demanda();
        demanda.setId(dto.idDemanda());
        
        return DemandaTipo.builder()
                .id(dto.id())
                .demanda(demanda)
                .tipoSaude(dto.tipoSaude())
                .descricaoTipoSaude(dto.descricaoTipoSaude())
                .tipoInfraestrutura(dto.tipoInfraestrutura())
                .descricaoTipoInfraestrutura(dto.descricaoTipoInfraestrutura())
                .tipoEducacao(dto.tipoEducacao())
                .descricaoTipoEducacao(dto.descricaoTipoEducacao())
                .tipoSeguranca(dto.tipoSeguranca())
                .descricaoTipoSeguranca(dto.descricaoTipoSeguranca())
                .tipoOutros(dto.tipoOutros())
                .descricaoTipoOutros(dto.descricaoTipoOutros())
                .build();
    }
}