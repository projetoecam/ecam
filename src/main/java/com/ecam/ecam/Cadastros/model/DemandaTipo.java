package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa as flags e descrições dos Tipos de uma Demanda.
 */
@Entity
@Table(name = "tb_demandaTipo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandaTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relacionamento obrigatório com Demanda (carregamento LAZY para performance)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_demanda", nullable = false)
    private Demanda demanda;

    @Column(name = "tipo_saude")
    private Boolean tipoSaude;

    @Column(name = "descricao_tipo_saude")
    private String descricaoTipoSaude;

    @Column(name = "tipo_infraestrutura")
    private Boolean tipoInfraestrutura;

    @Column(name = "descricao_tipo_infraestrutura")
    private String descricaoTipoInfraestrutura;

    @Column(name = "tipo_educacao")
    private Boolean tipoEducacao;

    @Column(name = "descricao_tipo_educacao")
    private String descricaoTipoEducacao;

    @Column(name = "tipo_seguranca")
    private Boolean tipoSeguranca;

    @Column(name = "descricao_tipo_seguranca")
    private String descricaoTipoSeguranca;

    @Column(name = "tipo_outros")
    private Boolean tipoOutros;

    @Column(name = "descricao_tipo_outros")
    private String descricaoTipoOutros;
}