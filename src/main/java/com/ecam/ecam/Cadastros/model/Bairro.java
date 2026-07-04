package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "tb_bairro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Bairro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_macro_regiao", nullable = false)
   private MacroRegiao macroRegiao;
    
}
