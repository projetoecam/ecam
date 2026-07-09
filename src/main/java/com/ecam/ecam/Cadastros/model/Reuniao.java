package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_reuniao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reuniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_reuniao", nullable = false)
    private LocalDate dataReuniao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comunidade", nullable = false)
    private Comunidade comunidade;

    @Column(name = "tema_reuniao", nullable = false, length = 200)
    private String temaReuniao;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lider_responsavel", nullable = false)
    private Pessoa liderResponsavel;

    @Column(name = "deputado_presente")
    private Boolean deputadoPresente;

    @Column(name = "representante_presente")
    private Boolean representantePresente;

    @Column(name = "nome_representante", length = 150)
    private String nomeRepresentante;

}