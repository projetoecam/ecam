package com.ecam.ecam.Cadastros.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandaDTO {

    private Integer id;
    private Integer numeroSequencial;
    private Integer ano;
    private Integer idSolicitante;
    private Integer idComunidade;
    private Integer idLiderResponsavel;
    private String orgaoResponsavel;
    private String status;
    private LocalDate dataSolicitacao;
    private Long idOperador;

}