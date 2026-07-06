package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_reuniao_presenca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReuniaoPresenca {

    @EmbeddedId
    private ReuniaoPresencaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idReuniao") // Mapeia o atributo da chave composta
    @JoinColumn(name = "id_reuniao")
    private Reuniao reuniao;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPessoa") // Mapeia o atributo da chave composta
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(name = "assinatura_confirmada")
    private Boolean assinaturaConfirmada;

}