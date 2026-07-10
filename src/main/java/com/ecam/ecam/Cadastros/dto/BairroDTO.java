package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;

@Builder
public record BairroDTO( 

     Integer id,
     String nome,
     Integer idMacroRegiao

){}