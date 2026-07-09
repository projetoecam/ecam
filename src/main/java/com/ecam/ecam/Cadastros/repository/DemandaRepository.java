package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Integer> {
    
    @Query("SELECT COALESCE(MAX(d.numeroSequencial), 0) FROM Demanda d WHERE d.ano = :ano")
    Integer findMaxNumeroSequencialByAno(@Param("ano") Integer ano);
}