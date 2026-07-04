package com.ecam.ecam.Cadastros.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BairroDTO {

    private Integer id;
    private String nome;
    private Integer idMacroRegiao;

}