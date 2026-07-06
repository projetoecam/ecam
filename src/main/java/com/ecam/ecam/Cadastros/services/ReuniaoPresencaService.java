package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.ReuniaoPresencaDTO;
import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.Cadastros.model.Reuniao;
import com.ecam.ecam.Cadastros.model.ReuniaoPresenca;
import com.ecam.ecam.Cadastros.model.ReuniaoPresencaId;
import com.ecam.ecam.Cadastros.repository.ReuniaoPresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReuniaoPresencaService {

    @Autowired
    private ReuniaoPresencaRepository repository;

    public List<ReuniaoPresencaDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }
    
    public List<ReuniaoPresencaDTO> listarPorReuniao(Integer idReuniao) {
        return repository.findByIdIdReuniao(idReuniao).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ReuniaoPresencaDTO> listarPorPessoa(Integer idPessoa) {
        return repository.findByIdIdPessoa(idPessoa).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ReuniaoPresencaDTO registrarPresenca(ReuniaoPresencaDTO dto) {
        ReuniaoPresenca entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    // Método para atualizar a assinatura caso a presença já exista
    public ReuniaoPresencaDTO atualizarAssinatura(Integer idReuniao, Integer idPessoa, Boolean confirmada) {
        ReuniaoPresencaId idComposto = new ReuniaoPresencaId(idReuniao, idPessoa);
        ReuniaoPresenca entidade = repository.findById(idComposto)
                .orElseThrow(() -> new RuntimeException("Registro de presença não encontrado!"));
        
        entidade.setAssinaturaConfirmada(confirmada);
        return converterParaDTO(repository.save(entidade));
    }

    public void removerPresenca(Integer idReuniao, Integer idPessoa) {
        ReuniaoPresencaId idComposto = new ReuniaoPresencaId(idReuniao, idPessoa);
        repository.deleteById(idComposto);
    }

    // --- Métodos de Conversão ---

    private ReuniaoPresencaDTO converterParaDTO(ReuniaoPresenca entidade) {
        return ReuniaoPresencaDTO.builder()
                .idReuniao(entidade.getId().getIdReuniao())
                .idPessoa(entidade.getId().getIdPessoa())
                .assinaturaConfirmada(entidade.getAssinaturaConfirmada())
                .build();
    }

    private ReuniaoPresenca converterParaEntidade(ReuniaoPresencaDTO dto) {
        ReuniaoPresencaId idComposto = new ReuniaoPresencaId(dto.getIdReuniao(), dto.getIdPessoa());
        
        Reuniao reuniao = new Reuniao();
        reuniao.setId(dto.getIdReuniao());

        Pessoa pessoa = new Pessoa();
        pessoa.setId(dto.getIdPessoa());

        return ReuniaoPresenca.builder()
                .id(idComposto)
                .reuniao(reuniao)
                .pessoa(pessoa)
                .assinaturaConfirmada(dto.getAssinaturaConfirmada())
                .build();
    }
}