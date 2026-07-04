package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Integer> {
}