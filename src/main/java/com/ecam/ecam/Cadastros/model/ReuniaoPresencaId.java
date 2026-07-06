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
public class ReuniaoPresencaId implements Serializable {

    @Column(name = "id_reuniao")
    private Integer idReuniao;

    @Column(name = "id_pessoa")
    private Integer idPessoa;

}