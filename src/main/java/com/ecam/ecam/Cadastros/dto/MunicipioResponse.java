package com.ecam.ecam.Cadastros.dto;

import com.ecam.ecam.Cadastros.model.Municipio;

public record MunicipioResponse(
    Integer id,
    String nome,
    String uf
) {
    // Factory method prático para conversão
    public static MunicipioResponse fromEntity(Municipio municipio) {
        return new MunicipioResponse(
            municipio.getId(),
            municipio.getNome(),
            municipio.getUf()
        );
    }
}
