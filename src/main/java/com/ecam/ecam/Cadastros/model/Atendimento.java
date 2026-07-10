package com.ecam.ecam.Cadastros.model;
import com.ecam.ecam.login.model.Usuario;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_atendimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_cadastro", nullable = false)
    private Usuario usuarioCadastro;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "motivo_contato", nullable = false, length = 255)
    private String motivoContato;

    @Column(name = "resultado_contato", nullable = false, columnDefinition = "TEXT")
    private String resultadoContato;

    @Column(name = "necessita_retorno")
    private Boolean necessitaRetorno;

    @Column(name = "data_proximo_retorno")
    private LocalDate dataProximoRetorno;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

}