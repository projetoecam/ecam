package com.ecam.ecam.Cadastros.repository;
import com.ecam.ecam.Cadastros.model.Pessoas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Long> {
    
}