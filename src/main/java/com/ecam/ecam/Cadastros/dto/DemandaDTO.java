package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;
import java.time.LocalDate;


@Builder
public record DemandaDTO (

     Integer id,
     Integer numeroSequencial,
     Integer ano,
     Integer idSolicitante,
     Integer idComunidade,
     Integer idLiderResponsavel,
     String orgaoResponsavel,
     String status,
     LocalDate dataSolicitacao,
     Long idOperador

) {}