package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.MunicipioDTO;
import com.ecam.ecam.Cadastros.exception.RecursoJaCadastradoException;
import com.ecam.ecam.Cadastros.model.Municipio;
import com.ecam.ecam.Cadastros.repository.MunicipioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MunicipioService {


    private final MunicipioRepository repository;

    public List<MunicipioDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public MunicipioDTO buscarPorId(Integer id) {
        Municipio entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Município não encontrado!"));
        return converterParaDTO(entidade);
    }

    public MunicipioDTO salvar(MunicipioDTO dto) {

        DadosFormatados dados = retornaMaiusculo(dto.nome(), dto.uf());
        String nomeSalvar = dados.nome(); 
        String ufSalvar = dados.uf();   

        if (repository.countByNomeIgnoreCaseAndUfIgnoreCase(nomeSalvar, ufSalvar) > 0) {
            throw new RecursoJaCadastradoException("O município '" + nomeSalvar + "' já está cadastrado para o estado " + ufSalvar + ".");
        }   
        Municipio entidade = converterParaEntidade(dto);
        entidade.setNome(nomeSalvar);
        entidade.setUf(ufSalvar);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public MunicipioDTO atualizar(Integer id, MunicipioDTO dto) {
        Municipio entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Município não encontrado!"));

        
        DadosFormatados dados = retornaMaiusculo(dto.nome(), dto.uf());
        String nomeSalvar = dados.nome(); 
        String ufSalvar = dados.uf();   

        entidadeExistente.setNome(nomeSalvar);
        entidadeExistente.setUf(ufSalvar);

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    // --- Métodos de Conversão ---

    private MunicipioDTO converterParaDTO(Municipio entidade) {
        return MunicipioDTO.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .uf(entidade.getUf())
                .build();
    }

    private Municipio converterParaEntidade(MunicipioDTO dto) {
        return Municipio.builder()
                .id(dto.id())
                .nome(dto.nome())
                .uf(dto.uf())
                .build();
    }

    public record DadosFormatados(String nome, String uf) {}

    public DadosFormatados retornaMaiusculo(String nome, String uf) {
        return new DadosFormatados(
                nome.trim().toUpperCase(),
                uf.trim().toUpperCase()
        );
    }
}