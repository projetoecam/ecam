package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.ReuniaoDTO;
import com.ecam.ecam.Cadastros.model.Comunidade;
import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.Cadastros.model.Reuniao;
import com.ecam.ecam.Cadastros.repository.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReuniaoService {

    @Autowired
    private ReuniaoRepository repository;

    public List<ReuniaoDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ReuniaoDTO> listarPorComunidade(Integer idComunidade) {
        return repository.findByComunidadeId(idComunidade).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ReuniaoDTO buscarPorId(Integer id) {
        Reuniao entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reunião não encontrada!"));
        return converterParaDTO(entidade);
    }

    public ReuniaoDTO salvar(ReuniaoDTO dto) {
        Reuniao entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public ReuniaoDTO atualizar(Integer id, ReuniaoDTO dto) {
        Reuniao entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reunião não encontrada!"));

        entidadeExistente.setDataReuniao(dto.getDataReuniao());
        entidadeExistente.setTemaReuniao(dto.getTemaReuniao());
        entidadeExistente.setDescricao(dto.getDescricao());
        entidadeExistente.setDeputadoPresente(dto.getDeputadoPresente());
        entidadeExistente.setRepresentantePresente(dto.getRepresentantePresente());
        entidadeExistente.setNomeRepresentante(dto.getNomeRepresentante());

        if (dto.getIdComunidade() != null) {
            Comunidade comunidade = new Comunidade();
            comunidade.setId(dto.getIdComunidade());
            entidadeExistente.setComunidade(comunidade);
        }

        if (dto.getIdLiderResponsavel() != null) {
            Pessoa lider = new Pessoa();
            lider.setId(dto.getIdLiderResponsavel());
            entidadeExistente.setLiderResponsavel(lider);
        }

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    // --- Métodos de Conversão ---

    private ReuniaoDTO converterParaDTO(Reuniao entidade) {
        return ReuniaoDTO.builder()
                .id(entidade.getId())
                .dataReuniao(entidade.getDataReuniao())
                .idComunidade(entidade.getComunidade() != null ? entidade.getComunidade().getId() : null)
                .temaReuniao(entidade.getTemaReuniao())
                .descricao(entidade.getDescricao())
                .idLiderResponsavel(entidade.getLiderResponsavel() != null ? entidade.getLiderResponsavel().getId() : null)
                .deputadoPresente(entidade.getDeputadoPresente())
                .representantePresente(entidade.getRepresentantePresente())
                .nomeRepresentante(entidade.getNomeRepresentante())
                .build();
    }

    private Reuniao converterParaEntidade(ReuniaoDTO dto) {
        Reuniao entidade = Reuniao.builder()
                .id(dto.getId())
                .dataReuniao(dto.getDataReuniao())
                .temaReuniao(dto.getTemaReuniao())
                .descricao(dto.getDescricao())
                .deputadoPresente(dto.getDeputadoPresente())
                .representantePresente(dto.getRepresentantePresente())
                .nomeRepresentante(dto.getNomeRepresentante())
                .build();

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

        return entidade;
    }
}