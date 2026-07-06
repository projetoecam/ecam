package com.ecam.ecam.Cadastros.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LiderancaDTO {

    private Integer idPessoa;
    private String tipoLideranca;
    private String classificacao;
    private Integer qtdPessoasMobiliza;
    private String historicoPolitico;

}