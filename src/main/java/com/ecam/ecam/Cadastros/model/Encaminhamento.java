package com.ecam.ecam.Cadastros.model;
import com.ecam.ecam.login.model.Usuario;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_encaminhamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Encaminhamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_demanda", nullable = false)
    private Demanda demanda;

    @Column(name = "numero_protocolo", length = 50)
    private String numeroProtocolo;

    @Column(name = "orgao_destinatario", nullable = false, length = 150)
    private String orgaoDestinatario;

    @Column(name = "data_envio", nullable = false)
    private LocalDate dataEnvio;

    @Column(name = "anexos_url", columnDefinition = "VARCHAR(MAX)")
    private String anexosUrl;

    @Column(name = "resposta_recebida", columnDefinition = "VARCHAR(MAX)")
    private String respostaRecebida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operador", nullable = false)
    private Usuario operador;

}