package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.DemandaTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandaTipoRepository extends JpaRepository<DemandaTipo, Integer> {
    // Busca customizada útil: Listar os tipos vinculados a uma demanda específica
    List<DemandaTipo> findByDemandaId(Integer idDemanda);
}