package com.ecam.ecam.Cadastros.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncaminhamentoDTO {

    private Integer id;
    private Integer idDemanda;
    private String numeroProtocolo;
    private String orgaoDestinatario;
    private LocalDate dataEnvio;
    private String anexosUrl;
    private String respostaRecebida;
    private Long idOperador;

}