package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}