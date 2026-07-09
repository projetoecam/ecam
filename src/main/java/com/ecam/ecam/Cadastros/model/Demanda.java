package com.ecam.ecam.Cadastros.model;

import com.ecam.ecam.login.model.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade principal de Demanda que gerencia as solicitações.
 */
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

    @Column(name = "tipo_demanda", nullable = false, length = 100)
    private String tipoDemanda;

    @Column(name = "descricao_demanda", nullable = false, columnDefinition = "TEXT")
    private String descricaoDemanda;

    @Column(name = "orgao_responsavel", length = 150)
    private String orgaoResponsavel;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "data_solicitacao", nullable = false)
    private LocalDate dataSolicitacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operador", nullable = false)
    private Usuario operador;

    // Relacionamento 1-para-Muitos: Quando deletar a Demanda, deleta os Tipos associados.
    @OneToMany(mappedBy = "demanda", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DemandaTipo> tipos = new ArrayList<>();

    /**
     * Método utilitário para garantir o vínculo bidirecional.
     */
    public void adicionarTipo(DemandaTipo tipo){
        tipos.add(tipo);
        tipo.setDemanda(this);
    }
}