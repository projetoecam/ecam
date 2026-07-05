package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_comunidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bairro", nullable = false)
    private Bairro bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_macro_regiao")
    private MacroRegiao macroRegiao;

    @Column(length = 10)
    private String cep;

    @Column(name = "endereco_principal", length = 255)
    private String enderecoPrincipal;

    @Column(name = "ponto_referencia", length = 255)
    private String pontoReferencia;

    @Column(name = "qtd_aproximada_moradores")
    private Integer qtdAproximadaMoradores;

    @Column(name = "grau_prioridade", length = 50)
    private String grauPrioridade;

    @Column(length = 50)
    private String classificacao;

}