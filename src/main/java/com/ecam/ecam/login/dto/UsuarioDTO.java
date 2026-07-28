package com.ecam.ecam.login.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String login_usuario; 
    private String senha_hash;    
    private String perfil;
    private Boolean ativo;

}