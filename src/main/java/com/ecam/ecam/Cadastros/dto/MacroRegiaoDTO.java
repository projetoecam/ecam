package com.ecam.ecam.Cadastros.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MacroRegiaoDTO {

    private Integer id;
    private Integer idMunicipio;
    private String nomeMunicipio; 
    private String nome;
    private String regiaoApelido;
    
}