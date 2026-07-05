package com.ecam.ecam.Cadastros.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComunidadeDTO {

    private Integer id;
    private String nome;
    private Integer idBairro;
    private Integer idMacroRegiao;
    private String cep;
    private String enderecoPrincipal;
    private String pontoReferencia;
    private Integer qtdAproximadaMoradores;
    private String grauPrioridade;
    private String classificacao;

}