package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.DemandaDTO;
import com.ecam.ecam.Cadastros.model.Comunidade;
import com.ecam.ecam.Cadastros.model.Demanda;
import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.login.model.Usuario;

import lombok.RequiredArgsConstructor;

import com.ecam.ecam.Cadastros.repository.DemandaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DemandaService {


    private final DemandaRepository repository;

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

    @Transactional
    public DemandaDTO salvar(DemandaDTO dto) {
        Demanda entidade = converterParaEntidade(dto);
        
        // Regras de preenchimento automático para novos registros
        if (entidade.getAno() == null) {
            entidade.setAno(LocalDate.now().getYear());
        }
        if (entidade.getDataSolicitacao() == null) {
            entidade.setDataSolicitacao(LocalDate.now());
        }
        if (entidade.getNumeroSequencial() == null) {
            Integer ultimoNumero = repository.findMaxNumeroSequencialByAno(entidade.getAno());
            entidade.setNumeroSequencial(ultimoNumero + 1);
        }

        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    @Transactional
    public DemandaDTO atualizar(Integer id, DemandaDTO dto) {
        Demanda entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demanda não encontrada!"));

        // Atualização dos campos de dados
        entidadeExistente.setNumeroSequencial(dto.numeroSequencial());
        entidadeExistente.setAno(dto.ano());
        entidadeExistente.setOrgaoResponsavel(dto.orgaoResponsavel());
        entidadeExistente.setStatus(dto.status());
        entidadeExistente.setDataSolicitacao(dto.dataSolicitacao());

        // Atualização dos relacionamentos
        if (dto.idSolicitante() != null) {
            Pessoa solicitante = new Pessoa();
            solicitante.setId(dto.idSolicitante());
            entidadeExistente.setSolicitante(solicitante);
        }
        if (dto.idComunidade() != null) {
            Comunidade comunidade = new Comunidade();
            comunidade.setId(dto.idComunidade());
            entidadeExistente.setComunidade(comunidade);
        }
        if (dto.idLiderResponsavel() != null) {
            Pessoa lider = new Pessoa();
            lider.setId(dto.idLiderResponsavel());
            entidadeExistente.setLiderResponsavel(lider);
        } else {
            entidadeExistente.setLiderResponsavel(null);
        }
        if (dto.idOperador() != null) {
            Usuario operador = new Usuario();
            operador.setId(dto.idOperador());
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
                .id(dto.id())
                .numeroSequencial(dto.numeroSequencial())
                .ano(dto.ano())
                .orgaoResponsavel(dto.orgaoResponsavel())
                .status(dto.status())
                .dataSolicitacao(dto.dataSolicitacao())
                .build();

        if (dto.idSolicitante() != null) {
            Pessoa solicitante = new Pessoa();
            solicitante.setId(dto.idSolicitante());
            entidade.setSolicitante(solicitante);
        }
        if (dto.idComunidade() != null) {
            Comunidade comunidade = new Comunidade();
            comunidade.setId(dto.idComunidade());
            entidade.setComunidade(comunidade);
        }
        if (dto.idLiderResponsavel() != null) {
            Pessoa lider = new Pessoa();
            lider.setId(dto.idLiderResponsavel());
            entidade.setLiderResponsavel(lider);
        }
        if (dto.idOperador() != null) {
            Usuario operador = new Usuario();
            operador.setId(dto.idOperador());
            entidade.setOperador(operador);
        }

        return entidade;
    }
}