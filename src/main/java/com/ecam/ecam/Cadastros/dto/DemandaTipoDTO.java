package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;


@Builder
public record DemandaTipoDTO (
     Integer id,
     Integer idDemanda,
     Boolean tipoSaude,
     String descricaoTipoSaude,
     Boolean tipoInfraestrutura,
     String descricaoTipoInfraestrutura,
     Boolean tipoEducacao,
     String descricaoTipoEducacao,
     Boolean tipoSeguranca,
     String descricaoTipoSeguranca,
     Boolean tipoOutros,
     String descricaoTipoOutros
) {}