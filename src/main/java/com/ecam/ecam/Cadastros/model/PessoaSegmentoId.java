package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class PessoaSegmentoId implements Serializable {

    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @Column(name = "id_segmento")
    private Integer idSegmento;

}