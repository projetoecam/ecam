package com.ecam.ecam.Cadastros.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReuniaoPresencaDTO {

    private Integer idReuniao;
    private Integer idPessoa;
    private Boolean assinaturaConfirmada;

}