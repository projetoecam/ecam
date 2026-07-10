package com.ecam.ecam.Cadastros.dto;

import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record PessoaDTO (

     Integer id,    
     String nomeCompleto,   
     String cpf,    
     String tituloEleitor,  
     String nomeMae,    
     LocalDate dataNascimento,  
     String telefone,   
     String whatsapp,   
     Integer idComunidade,  
     String enderecoCompleto,   
     String cep,    
     String origemCadastro, 
     Integer idLiderResponsavel,    
     Integer idLiderRegional,   
     String status, 
     String observacoes,    
     Long idUsuarioCadastro,    
     LocalDateTime dataCadastro   

){}