package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.EncaminhamentoDTO;
import com.ecam.ecam.Cadastros.model.Demanda;
import com.ecam.ecam.Cadastros.model.Encaminhamento;
import com.ecam.ecam.login.model.Usuario;
import com.ecam.ecam.Cadastros.repository.EncaminhamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EncaminhamentoService {

    @Autowired
    private EncaminhamentoRepository repository;

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

        entidadeExistente.setNumeroProtocolo(dto.getNumeroProtocolo());
        entidadeExistente.setOrgaoDestinatario(dto.getOrgaoDestinatario());
        entidadeExistente.setDataEnvio(dto.getDataEnvio());
        entidadeExistente.setAnexosUrl(dto.getAnexosUrl());
        entidadeExistente.setRespostaRecebida(dto.getRespostaRecebida());

        if (dto.getIdDemanda() != null) {
            Demanda demanda = new Demanda();
            demanda.setId(dto.getIdDemanda());
            entidadeExistente.setDemanda(demanda);
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
                .id(dto.getId())
                .numeroProtocolo(dto.getNumeroProtocolo())
                .orgaoDestinatario(dto.getOrgaoDestinatario())
                .dataEnvio(dto.getDataEnvio())
                .anexosUrl(dto.getAnexosUrl())
                .respostaRecebida(dto.getRespostaRecebida())
                .build();

        if (dto.getIdDemanda() != null) {
            Demanda demanda = new Demanda();
            demanda.setId(dto.getIdDemanda());
            entidade.setDemanda(demanda);
        }

        if (dto.getIdOperador() != null) {
            Usuario operador = new Usuario();
            operador.setId(dto.getIdOperador());
            entidade.setOperador(operador);
        }

        return entidade;
    }
}