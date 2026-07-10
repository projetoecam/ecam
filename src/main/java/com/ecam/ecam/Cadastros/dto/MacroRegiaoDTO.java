package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;
@Builder

public record MacroRegiaoDTO(

    Integer id,
    Integer idMunicipio,
    String nomeMunicipio,
    String nome,
    String regiaoApelido
    
) {}