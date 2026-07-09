package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;

@Builder

public record ComunidadeDTO(

        Integer id,
        String nome,
        Integer idBairro,
        Integer idMacroRegiao,
        String cep,
        String enderecoPrincipal,
        String pontoReferencia,
        Integer qtdAproximadaMoradores,
        String grauPrioridade,
        String classificacao

) {
}