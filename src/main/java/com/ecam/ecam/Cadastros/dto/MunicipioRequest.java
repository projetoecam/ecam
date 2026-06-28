package com.ecam.ecam.Cadastros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MunicipioRequest(
    @NotBlank(message = "O nome do município é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String nome,

    @Size(min = 2, max = 2, message = "A UF deve conter exatamente 2 caracteres")
    String uf
) {}