package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_pessoa_segmento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaSegmento {

    @EmbeddedId
    private PessoaSegmentoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPessoa") // Mapeia o atributo da chave composta
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSegmento") // Mapeia o atributo da chave composta
    @JoinColumn(name = "id_segmento")
    private Segmento segmento;

}
