package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.BairroDTO;
import com.ecam.ecam.Cadastros.model.Bairro;
import com.ecam.ecam.Cadastros.model.MacroRegiao;
import com.ecam.ecam.Cadastros.repository.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BairroService {

    @Autowired
    private BairroRepository repository;

    public List<BairroDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public BairroDTO buscarPorId(Integer id) {
        Bairro entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bairro não encontrado!"));
        return converterParaDTO(entidade);
    }

    public BairroDTO salvar(BairroDTO dto) {
        Bairro entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public BairroDTO atualizar(Integer id, BairroDTO dto) {
        Bairro entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bairro não encontrado!"));

        entidadeExistente.setNome(dto.getNome());
        
        // Atualiza a referência da Macro Região, se o ID for fornecido
        if (dto.getIdMacroRegiao() != null) {
            MacroRegiao macroRegiao = new MacroRegiao();
            macroRegiao.setId(dto.getIdMacroRegiao());
            entidadeExistente.setMacroRegiao(macroRegiao);
        }

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    // --- Métodos de Conversão ---

    private BairroDTO converterParaDTO(Bairro entidade) {
        return BairroDTO.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                // Pega o ID da Macro Região caso não seja nula
                .idMacroRegiao(entidade.getMacroRegiao() != null ? entidade.getMacroRegiao().getId() : null)
                .build();
    }

    private Bairro converterParaEntidade(BairroDTO dto) {
        Bairro entidade = Bairro.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .build();

        // Cria a referência para salvar o relacionamento sem carregar tudo do banco
        if (dto.getIdMacroRegiao() != null) {
            MacroRegiao macroRegiao = new MacroRegiao();
            macroRegiao.setId(dto.getIdMacroRegiao());
            entidade.setMacroRegiao(macroRegiao);
        }
        
        return entidade;
    }
}