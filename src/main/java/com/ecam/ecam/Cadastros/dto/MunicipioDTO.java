package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;

@Builder
public record MunicipioDTO(
    Integer id,
    String nome,
    String uf
) {}