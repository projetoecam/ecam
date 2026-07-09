package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.EncaminhamentoDTO;
import com.ecam.ecam.Cadastros.model.Demanda;
import com.ecam.ecam.Cadastros.model.Encaminhamento;
import com.ecam.ecam.login.model.Usuario;

import lombok.RequiredArgsConstructor;

import com.ecam.ecam.Cadastros.repository.EncaminhamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EncaminhamentoService {


    private final EncaminhamentoRepository repository;

    public List<EncaminhamentoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }
    
    public List<EncaminhamentoDTO> listarPorDemanda(Integer idDemanda) {
        return repository.findByDemandaId(idDemanda).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public EncaminhamentoDTO buscarPorId(Integer id) {
        Encaminhamento entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encaminhamento não encontrado!"));
        return converterParaDTO(entidade);
    }

    public EncaminhamentoDTO salvar(EncaminhamentoDTO dto) {
        Encaminhamento entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public EncaminhamentoDTO atualizar(Integer id, EncaminhamentoDTO dto) {
        Encaminhamento entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encaminhamento não encontrado!"));

        entidadeExistente.setNumeroProtocolo(dto.numeroProtocolo());
        entidadeExistente.setOrgaoDestinatario(dto.orgaoDestinatario());
        entidadeExistente.setDataEnvio(dto.dataEnvio());
        entidadeExistente.setAnexosUrl(dto.anexosUrl());
        entidadeExistente.setRespostaRecebida(dto.respostaRecebida());

        if (dto.idDemanda() != null) {
            Demanda demanda = new Demanda();
            demanda.setId(dto.idDemanda());
            entidadeExistente.setDemanda(demanda);
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

    private EncaminhamentoDTO converterParaDTO(Encaminhamento entidade) {
        return EncaminhamentoDTO.builder()
                .id(entidade.getId())
                .idDemanda(entidade.getDemanda() != null ? entidade.getDemanda().getId() : null)
                .numeroProtocolo(entidade.getNumeroProtocolo())
                .orgaoDestinatario(entidade.getOrgaoDestinatario())
                .dataEnvio(entidade.getDataEnvio())
                .anexosUrl(entidade.getAnexosUrl())
                .respostaRecebida(entidade.getRespostaRecebida())
                .idOperador(entidade.getOperador() != null ? entidade.getOperador().getId() : null)
                .build();
    }

    private Encaminhamento converterParaEntidade(EncaminhamentoDTO dto) {
        Encaminhamento entidade = Encaminhamento.builder()
                .id(dto.id())
                .numeroProtocolo(dto.numeroProtocolo())
                .orgaoDestinatario(dto.orgaoDestinatario())
                .dataEnvio(dto.dataEnvio())
                .anexosUrl(dto.anexosUrl())
                .respostaRecebida(dto.respostaRecebida())
                .build();

        if (dto.idDemanda() != null) {
            Demanda demanda = new Demanda();
            demanda.setId(dto.idDemanda());
            entidade.setDemanda(demanda);
        }

        if (dto.idOperador() != null) {
            Usuario operador = new Usuario();
            operador.setId(dto.idOperador());
            entidade.setOperador(operador);
        }

        return entidade;
    }
}