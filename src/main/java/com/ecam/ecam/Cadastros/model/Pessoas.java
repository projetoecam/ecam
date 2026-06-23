package com.ecam.ecam.Cadastros.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pessoas")
public class Pessoas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "titulo_eleitor", nullable = false, length = 12)
    private String tituloEleitor;

    @Column(name = "nome_da_mae", nullable = false, length = 150)
    private String nomeDaMae;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(nullable = false, length = 15)
    private String whatsapp;

    @Column(name = "endereco_completo", nullable = false, columnDefinition = "VARCHAR(MAX)")
    private String enderecoCompleto;

    @Column(name = "ponto_referencia", columnDefinition = "VARCHAR(MAX)")
    private String pontoReferencia;

    @Column(name = "origem_cadastro", nullable = false, length = 100)
    private String origenCadastro;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "data_cadastro", insertable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "termo_consentimento_lgpd")
    private Boolean termoConsentimentoLgpd = false;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String observacoes;

    @Column(length = 1)
    private String sexo;

    // ==========================================
    // RELACIONAMENTOS MAPEADOS COM OBJETOS REAIS
    // ==========================================

    @ManyToOne
    @JoinColumn(name = "comunidade_id", nullable = false)
    private ComunidadeMicroterritorio comunidade;

    @ManyToOne
    @JoinColumn(name = "operador_cadastro_id", nullable = false)
    private Usuario operadorCadastro;

    // Como lideranças estão mapeadas na tabela liderancas_detalhes estendendo pessoas,
    // usamos o ID solto temporariamente ou o mapeamento da tabela de liderança se necessário.
    @Column(name = "lider_responsavel_id")
    private Long liderResponsavelId;

    @Column(name = "lider_regional_id")
    private Long liderRegionalId;

    public Pessoas() {}

    // Getters e Setters de todos os campos...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTituloEleitor() { return tituloEleitor; }
    public void setTituloEleitor(String tituloEleitor) { this.tituloEleitor = tituloEleitor; }
    public String getNomeDaMae() { return nomeDaMae; }
    public void setNomeDaMae(String nomeDaMae) { this.nomeDaMae = nomeDaMae; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getWhatsapp() { return whatsapp; }
    public void setWhatsapp(String whatsapp) { this.whatsapp = whatsapp; }
    public String getEnderecoCompleto() { return enderecoCompleto; }
    public void setEnderecoCompleto(String enderecoCompleto) { this.enderecoCompleto = enderecoCompleto; }
    public String getPontoReferencia() { return pontoReferencia; }
    public void setPontoReferencia(String pontoReferencia) { this.pontoReferencia = pontoReferencia; }
    public String getOrigenCadastro() { return origenCadastro; }
    public void setOrigenCadastro(String origenCadastro) { this.origenCadastro = origenCadastro; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
    public Boolean getTermoConsentimentoLgpd() { return termoConsentimentoLgpd; }
    public void setTermoConsentimentoLgpd(Boolean termoConsentimentoLgpd) { this.termoConsentimentoLgpd = termoConsentimentoLgpd; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public ComunidadeMicroterritorio getComunidade() { return comunidade; }
    public void setComunidade(ComunidadeMicroterritorio comunidade) { this.comunidade = comunidade; }
    public Usuario getOperadorCadastro() { return operadorCadastro; }
    public void setOperadorCadastro(Usuario operadorCadastro) { this.operadorCadastro = operadorCadastro; }
    public Long getLiderResponsavelId() { return liderResponsavelId; }
    public void setLiderResponsavelId(Long liderResponsavelId) { this.liderResponsavelId = liderResponsavelId; }
    public Long getLiderRegionalId() { return liderRegionalId; }
    public void setLiderRegionalId(Long liderRegionalId) { this.liderRegionalId = liderRegionalId; }
}