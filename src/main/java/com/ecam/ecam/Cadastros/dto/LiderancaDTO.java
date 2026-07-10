package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;


@Builder
public record LiderancaDTO(

     Integer idPessoa,
     String tipoLideranca,
     String classificacao,
     Integer qtdPessoasMobiliza,
     String historicoPolitico

){}