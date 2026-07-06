package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Segmento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentoRepository extends JpaRepository<Segmento, Integer> {
}