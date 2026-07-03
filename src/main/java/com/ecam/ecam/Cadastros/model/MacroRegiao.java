package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "tb_macro_regiao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MacroRegiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio", nullable = false)
    private Municipio municipio;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String regiao_apelido;
    
}
