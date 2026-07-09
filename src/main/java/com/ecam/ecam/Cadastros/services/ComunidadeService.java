package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.ComunidadeDTO;
import com.ecam.ecam.Cadastros.model.Bairro;
import com.ecam.ecam.Cadastros.model.Comunidade;
import com.ecam.ecam.Cadastros.model.MacroRegiao;
import com.ecam.ecam.Cadastros.repository.ComunidadeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComunidadeService {

    
    private final ComunidadeRepository repository;

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

        entidadeExistente.setNome(dto.nome());
        entidadeExistente.setCep(dto.cep());
        entidadeExistente.setEnderecoPrincipal(dto.enderecoPrincipal());
        entidadeExistente.setPontoReferencia(dto.pontoReferencia());
        entidadeExistente.setQtdAproximadaMoradores(dto.qtdAproximadaMoradores());
        entidadeExistente.setGrauPrioridade(dto.grauPrioridade());
        entidadeExistente.setClassificacao(dto.classificacao());

        if (dto.idBairro() != null) {
            Bairro bairro = new Bairro();
            bairro.setId(dto.idBairro());
            entidadeExistente.setBairro(bairro);
        }

        if (dto.idMacroRegiao() != null) {
            MacroRegiao macroRegiao = new MacroRegiao();
            macroRegiao.setId(dto.idMacroRegiao());
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
                .id(dto.id())
                .nome(dto.nome())
                .cep(dto.cep())
                .enderecoPrincipal(dto.enderecoPrincipal())
                .pontoReferencia(dto.pontoReferencia())
                .qtdAproximadaMoradores(dto.qtdAproximadaMoradores())
                .grauPrioridade(dto.grauPrioridade())
                .classificacao(dto.classificacao())
                .build();

        if (dto.idBairro() != null) {
            Bairro bairro = new Bairro();
            bairro.setId(dto.idBairro());
            entidade.setBairro(bairro);
        }

        if (dto.idMacroRegiao() != null) {
            MacroRegiao macroRegiao = new MacroRegiao();
            macroRegiao.setId(dto.idMacroRegiao());
            entidade.setMacroRegiao(macroRegiao);
        }

        return entidade;
    }
}