package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Lideranca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiderancaRepository extends JpaRepository<Lideranca, Integer> {
}