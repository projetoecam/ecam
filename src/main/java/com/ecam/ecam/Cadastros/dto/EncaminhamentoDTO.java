package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;
import java.time.LocalDate;


@Builder
public record EncaminhamentoDTO(

     Integer id,
     Integer idDemanda,
     String numeroProtocolo,
     String orgaoDestinatario,
     LocalDate dataEnvio,
     String anexosUrl,
     String respostaRecebida,
     Long idOperador

){}