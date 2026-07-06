package com.ecam.ecam.Cadastros.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReuniaoDTO {

    private Integer id;
    private LocalDate dataReuniao;
    private Integer idComunidade;
    private String temaReuniao;
    private String descricao;
    private Integer idLiderResponsavel;
    private Boolean deputadoPresente;
    private Boolean representantePresente;
    private String nomeRepresentante;

}