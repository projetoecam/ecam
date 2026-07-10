package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;


@Builder
public record SegmentoDTO(
    
        Integer id,
        String nome
) {}