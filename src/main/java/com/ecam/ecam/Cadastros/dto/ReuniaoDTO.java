package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;
import java.time.LocalDate;


@Builder
public record ReuniaoDTO ( 

     Integer id,
     LocalDate dataReuniao,
     Integer idComunidade,
     String temaReuniao,
     String descricao,
     Integer idLiderResponsavel,
     Boolean deputadoPresente,
     Boolean representantePresente,
     String nomeRepresentante

){}