package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;


@Builder
public record PessoaSegmentoDTO (

     Integer idPessoa,
     Integer idSegmento

){}