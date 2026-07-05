package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.ComunidadeDTO;
import com.ecam.ecam.Cadastros.model.Bairro;
import com.ecam.ecam.Cadastros.model.Comunidade;
import com.ecam.ecam.Cadastros.model.MacroRegiao;
import com.ecam.ecam.Cadastros.repository.ComunidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComunidadeService {

    @Autowired
    private ComunidadeRepository repository;

    public List<ComunidadeDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ComunidadeDTO buscarPorId(Integer id) {
        Comunidade entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comunidade não encontrada!"));
        return converterParaDTO(entidade);
    }

    public ComunidadeDTO salvar(ComunidadeDTO dto) {
        Comunidade entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public ComunidadeDTO atualizar(Integer id, ComunidadeDTO dto) {
        Comunidade entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comunidade não encontrada!"));

        entidadeExistente.setNome(dto.getNome());
        entidadeExistente.setCep(dto.getCep());
        entidadeExistente.setEnderecoPrincipal(dto.getEnderecoPrincipal());
        entidadeExistente.setPontoReferencia(dto.getPontoReferencia());
        entidadeExistente.setQtdAproximadaMoradores(dto.getQtdAproximadaMoradores());
        entidadeExistente.setGrauPrioridade(dto.getGrauPrioridade());
        entidadeExistente.setClassificacao(dto.getClassificacao());

        if (dto.getIdBairro() != null) {
            Bairro bairro = new Bairro();
            bairro.setId(dto.getIdBairro());
            entidadeExistente.setBairro(bairro);
        }

        if (dto.getIdMacroRegiao() != null) {
            MacroRegiao macroRegiao = new MacroRegiao();
            macroRegiao.setId(dto.getIdMacroRegiao());
            entidadeExistente.setMacroRegiao(macroRegiao);
        } else {
            entidadeExistente.setMacroRegiao(null);
        }

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    // --- Métodos de Conversão ---

    private ComunidadeDTO converterParaDTO(Comunidade entidade) {
        return ComunidadeDTO.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .idBairro(entidade.getBairro() != null ? entidade.getBairro().getId() : null)
                .idMacroRegiao(entidade.getMacroRegiao() != null ? entidade.getMacroRegiao().getId() : null)
                .cep(entidade.getCep())
                .enderecoPrincipal(entidade.getEnderecoPrincipal())
                .pontoReferencia(entidade.getPontoReferencia())
                .qtdAproximadaMoradores(entidade.getQtdAproximadaMoradores())
                .grauPrioridade(entidade.getGrauPrioridade())
                .classificacao(entidade.getClassificacao())
                .build();
    }

    private Comunidade converterParaEntidade(ComunidadeDTO dto) {
        Comunidade entidade = Comunidade.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cep(dto.getCep())
                .enderecoPrincipal(dto.getEnderecoPrincipal())
                .pontoReferencia(dto.getPontoReferencia())
                .qtdAproximadaMoradores(dto.getQtdAproximadaMoradores())
                .grauPrioridade(dto.getGrauPrioridade())
                .classificacao(dto.getClassificacao())
                .build();

        if (dto.getIdBairro() != null) {
            Bairro bairro = new Bairro();
            bairro.setId(dto.getIdBairro());
            entidade.setBairro(bairro);
        }

        if (dto.getIdMacroRegiao() != null) {
            MacroRegiao macroRegiao = new MacroRegiao();
            macroRegiao.setId(dto.getIdMacroRegiao());
            entidade.setMacroRegiao(macroRegiao);
        }

        return entidade;
    }
}