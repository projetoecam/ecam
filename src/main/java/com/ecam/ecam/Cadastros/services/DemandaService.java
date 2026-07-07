package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.DemandaDTO;
import com.ecam.ecam.Cadastros.model.Comunidade;
import com.ecam.ecam.Cadastros.model.Demanda;
import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.login.model.Usuario;
import com.ecam.ecam.Cadastros.repository.DemandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemandaService {

    @Autowired
    private DemandaRepository repository;

    public List<DemandaDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public DemandaDTO buscarPorId(Integer id) {
        Demanda entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demanda não encontrada!"));
        return converterParaDTO(entidade);
    }

    public DemandaDTO salvar(DemandaDTO dto) {
        Demanda entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public DemandaDTO atualizar(Integer id, DemandaDTO dto) {
        Demanda entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demanda não encontrada!"));

        entidadeExistente.setNumeroSequencial(dto.getNumeroSequencial());
        entidadeExistente.setAno(dto.getAno());
        entidadeExistente.setOrgaoResponsavel(dto.getOrgaoResponsavel());
        entidadeExistente.setStatus(dto.getStatus());
        entidadeExistente.setDataSolicitacao(dto.getDataSolicitacao());

        if (dto.getIdSolicitante() != null) {
            Pessoa solicitante = new Pessoa();
            solicitante.setId(dto.getIdSolicitante());
            entidadeExistente.setSolicitante(solicitante);
        }

        if (dto.getIdComunidade() != null) {
            Comunidade comunidade = new Comunidade();
            comunidade.setId(dto.getIdComunidade());
            entidadeExistente.setComunidade(comunidade);
        }

        if (dto.getIdLiderResponsavel() != null) {
            Pessoa lider = new Pessoa();
            lider.setId(dto.getIdLiderResponsavel());
            entidadeExistente.setLiderResponsavel(lider);
        } else {
            entidadeExistente.setLiderResponsavel(null);
        }

        if (dto.getIdOperador() != null) {
            Usuario operador = new Usuario();
            operador.setId(dto.getIdOperador());
            entidadeExistente.setOperador(operador);
        }

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    // --- Métodos de Conversão ---

    private DemandaDTO converterParaDTO(Demanda entidade) {
        return DemandaDTO.builder()
                .id(entidade.getId())
                .numeroSequencial(entidade.getNumeroSequencial())
                .ano(entidade.getAno())
                .idSolicitante(entidade.getSolicitante() != null ? entidade.getSolicitante().getId() : null)
                .idComunidade(entidade.getComunidade() != null ? entidade.getComunidade().getId() : null)
                .idLiderResponsavel(entidade.getLiderResponsavel() != null ? entidade.getLiderResponsavel().getId() : null)
                .orgaoResponsavel(entidade.getOrgaoResponsavel())
                .status(entidade.getStatus())
                .dataSolicitacao(entidade.getDataSolicitacao())
                .idOperador(entidade.getOperador() != null ? entidade.getOperador().getId() : null)
                .build();
    }

    private Demanda converterParaEntidade(DemandaDTO dto) {
        Demanda entidade = Demanda.builder()
                .id(dto.getId())
                .numeroSequencial(dto.getNumeroSequencial())
                .ano(dto.getAno())
                .orgaoResponsavel(dto.getOrgaoResponsavel())
                .status(dto.getStatus())
                .dataSolicitacao(dto.getDataSolicitacao())
                .build();

        if (dto.getIdSolicitante() != null) {
            Pessoa solicitante = new Pessoa();
            solicitante.setId(dto.getIdSolicitante());
            entidade.setSolicitante(solicitante);
        }

        if (dto.getIdComunidade() != null) {
            Comunidade comunidade = new Comunidade();
            comunidade.setId(dto.getIdComunidade());
            entidade.setComunidade(comunidade);
        }

        if (dto.getIdLiderResponsavel() != null) {
            Pessoa lider = new Pessoa();
            lider.setId(dto.getIdLiderResponsavel());
            entidade.setLiderResponsavel(lider);
        }

        if (dto.getIdOperador() != null) {
            Usuario operador = new Usuario();
            operador.setId(dto.getIdOperador());
            entidade.setOperador(operador);
        }

        return entidade;
    }
}