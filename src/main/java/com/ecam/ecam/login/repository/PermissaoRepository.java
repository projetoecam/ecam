package com.ecam.ecam.login.repository;

import com.ecam.ecam.login.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    
    Optional<Permissao> findByNome(String nome);
    
}