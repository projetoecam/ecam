package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bairros")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "macroterritorio_id", nullable = false)
    private Macroterritorio macroterritorio;

    public Bairro() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Municipio getMunicipio() { return municipio; }
    public void setMunicipio(Municipio municipio) { this.municipio = municipio; }
    public Macroterritorio getMacroterritorio() { return macroterritorio; }
    public void setMacroterritorio(Macroterritorio macroterritorio) { this.macroterritorio = macroterritorio; }
}