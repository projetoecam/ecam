package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.ReuniaoDTO;
import com.ecam.ecam.Cadastros.model.Comunidade;
import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.Cadastros.model.Reuniao;
import com.ecam.ecam.Cadastros.repository.ReuniaoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReuniaoService {


    private final ReuniaoRepository repository;

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

        entidadeExistente.setDataReuniao(dto.dataReuniao());
        entidadeExistente.setTemaReuniao(dto.temaReuniao());
        entidadeExistente.setDescricao(dto.descricao());
        entidadeExistente.setDeputadoPresente(dto.deputadoPresente());
        entidadeExistente.setRepresentantePresente(dto.representantePresente());
        entidadeExistente.setNomeRepresentante(dto.nomeRepresentante());

        if (dto.idComunidade() != null) {
            Comunidade comunidade = new Comunidade();
            comunidade.setId(dto.idComunidade());
            entidadeExistente.setComunidade(comunidade);
        }

        if (dto.idLiderResponsavel() != null) {
            Pessoa lider = new Pessoa();
            lider.setId(dto.idLiderResponsavel());
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
                .id(dto.id())
                .dataReuniao(dto.dataReuniao())
                .temaReuniao(dto.temaReuniao())
                .descricao(dto.descricao())
                .deputadoPresente(dto.deputadoPresente())
                .representantePresente(dto.representantePresente())
                .nomeRepresentante(dto.nomeRepresentante())
                .build();

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

        return entidade;
    }
}