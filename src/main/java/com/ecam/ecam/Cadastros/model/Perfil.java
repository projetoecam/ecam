package com.ecam.ecam.Cadastros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "perfis")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nome;

    @Column(name = "permissao_apagar")
    private Boolean permissaoApagar = false;

    @Column(name = "permissao_exportar")
    private Boolean permissaoExportar = false;

    @Column(name = "permissao_copiar")
    private Boolean permissaoCopiar = false;

    public Perfil() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Boolean getPermissaoApagar() { return permissaoApagar; }
    public void setPermissaoApagar(Boolean permissaoApagar) { this.permissaoApagar = permissaoApagar; }
    public Boolean getPermissaoExportar() { return permissaoExportar; }
    public void setPermissaoExportar(Boolean permissaoExportar) { this.permissaoExportar = permissaoExportar; }
    public Boolean getPermissaoCopiar() { return permissaoCopiar; }
    public void setPermissaoCopiar(Boolean permissaoCopiar) { this.permissaoCopiar = permissaoCopiar; }
}