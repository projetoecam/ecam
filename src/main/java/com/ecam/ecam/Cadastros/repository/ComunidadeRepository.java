package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Comunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunidadeRepository extends JpaRepository<Comunidade, Integer> {
}