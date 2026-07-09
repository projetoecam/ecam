package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;


@Builder
public record ReuniaoPresencaDTO (

     Integer idReuniao,
     Integer idPessoa,
     Boolean assinaturaConfirmada

){}