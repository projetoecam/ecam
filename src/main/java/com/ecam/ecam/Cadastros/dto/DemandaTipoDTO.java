package com.ecam.ecam.Cadastros.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandaTipoDTO {

    private Integer id;
    private Integer idDemanda;
    private Boolean tipoSaude;
    private String descricaoTipoSaude;
    private Boolean tipoInfraestrutura;
    private String descricaoTipoInfraestrutura;
    private Boolean tipoEducacao;
    private String descricaoTipoEducacao;
    private Boolean tipoSeguranca;
    private String descricaoTipoSeguranca;
    private Boolean tipoOutros;
    private String descricaoTipoOutros;

}