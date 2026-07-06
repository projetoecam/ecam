package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_lideranca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lideranca {

    @Id
    @Column(name = "id_pessoa")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Indica que este relacionamento compartilha a chave primária
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(name = "tipo_lideranca", length = 50)
    private String tipoLideranca;

    @Column(length = 50)
    private String classificacao;

    @Column(name = "qtd_pessoas_mobiliza")
    private Integer qtdPessoasMobiliza;

    @Column(name = "historico_politico", columnDefinition = "VARCHAR(MAX)")
    private String historicoPolitico;

}