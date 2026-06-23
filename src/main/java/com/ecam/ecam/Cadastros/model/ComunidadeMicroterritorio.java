package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comunidades_microterritorios")
public class ComunidadeMicroterritorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "bairro_id", nullable = false)
    private Bairro bairro;

    @Column(length = 8)
    private String cep;

    @Column(name = "grau_prioridade", nullable = false, length = 20)
    private String grauPrioridade;

    @Column(name = "status_area", nullable = false, length = 30)
    private String statusArea;

    @Column(name = "qtd_aproximada_moradores")
    private Integer qtdAproximadaMoradores;

    public ComunidadeMicroterritorio() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Bairro getBairro() { return bairro; }
    public void setBairro(Bairro bairro) { this.bairro = bairro; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getGrauPrioridade() { return grauPrioridade; }
    public void setGrauPrioridade(String grauPrioridade) { this.grauPrioridade = grauPrioridade; }
    public String getStatusArea() { return statusArea; }
    public void setStatusArea(String statusArea) { this.statusArea = statusArea; }
    public Integer getQtdAproximadaMoradores() { return qtdAproximadaMoradores; }
    public void setQtdAproximadaMoradores(Integer qtdAproximadaMoradores) { this.qtdAproximadaMoradores = qtdAproximadaMoradores; }
}