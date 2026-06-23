package com.ecam.ecam.Cadastros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecam.ecam.Cadastros.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Nota: Certifique-se de ter uma classe 'Usuario.java' mapeada básica com id, nome_completo, email, senha_hash
}