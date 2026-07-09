package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
public record AtendimentoDTO (


     Integer id,
     Integer idPessoa,
     Long idUsuarioCadastro,
     LocalDateTime dataHora,
     String motivoContato,
     String resultadoContato,
     Boolean necessitaRetorno,
     LocalDate dataProximoRetorno,
     String observacoes

){}