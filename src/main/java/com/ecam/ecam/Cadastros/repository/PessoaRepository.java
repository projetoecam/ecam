package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}