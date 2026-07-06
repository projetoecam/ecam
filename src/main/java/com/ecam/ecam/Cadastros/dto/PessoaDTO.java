package com.ecam.ecam.Cadastros.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDTO {

    private Integer id;
    private String nomeCompleto;
    private String cpf;
    private String tituloEleitor;
    private String nomeMae;
    private LocalDate dataNascimento;
    private String telefone;
    private String whatsapp;
    private Integer idComunidade;
    private String enderecoCompleto;
    private String cep;
    private String origemCadastro;
    private Integer idLiderResponsavel;
    private Integer idLiderRegional;
    private String status;
    private String observacoes;
    private Long idUsuarioCadastro;
    private LocalDateTime dataCadastro;

}