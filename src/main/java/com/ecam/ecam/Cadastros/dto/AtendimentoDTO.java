package com.ecam.ecam.Cadastros.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtendimentoDTO {

    private Integer id;
    private Integer idPessoa;
    private Long idUsuarioCadastro;
    private LocalDateTime dataHora;
    private String motivoContato;
    private String resultadoContato;
    private Boolean necessitaRetorno;
    private LocalDate dataProximoRetorno;
    private String observacoes;

}