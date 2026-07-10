package com.ecam.ecam.Cadastros.model;
import com.ecam.ecam.login.model.Usuario;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_completo", nullable = false, length = 200)
    private String nomeCompleto;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(name = "titulo_eleitor", nullable = true, length = 20)
    private String tituloEleitor;

    @Column(name = "nome_mae", nullable = false, length = 200)
    private String nomeMae;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 20)
    private String whatsapp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comunidade", nullable = false)
    private Comunidade comunidade;

    @Column(name = "endereco_completo", nullable = false, length = 255)
    private String enderecoCompleto;

    @Column(nullable = false, length = 10)
    private String cep;

    @Column(name = "origem_cadastro", nullable = false, length = 100)
    private String origemCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lider_responsavel")
    private Pessoa liderResponsavel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lider_regional")
    private Pessoa liderRegional;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // Assumindo a existência de uma classe Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_cadastro", nullable = false)
    private Usuario usuarioCadastro;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

}