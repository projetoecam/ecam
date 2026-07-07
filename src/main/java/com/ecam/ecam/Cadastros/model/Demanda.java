package com.ecam.ecam.Cadastros.model;
import com.ecam.ecam.login.model.Usuario;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_demanda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Demanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_sequencial", nullable = false)
    private Integer numeroSequencial;

    @Column(nullable = false)
    private Integer ano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitante", nullable = false)
    private Pessoa solicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comunidade", nullable = false)
    private Comunidade comunidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lider_responsavel")
    private Pessoa liderResponsavel;

    @Column(name = "orgao_responsavel", length = 150)
    private String orgaoResponsavel;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "data_solicitacao", nullable = false)
    private LocalDate dataSolicitacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operador", nullable = false)
    private Usuario operador;

}