package com.ecam.ecam.login.repository;

import com.ecam.ecam.login.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Método para buscar o usuário no banco através do campo login_usuario.
    // Usamos @Query para garantir que o Spring entenda o nome exato da sua coluna.
    @Query("SELECT u FROM Usuario u WHERE u.login_usuario = :login")
    Optional<Usuario> buscarPorLogin(@Param("login") String login);
}