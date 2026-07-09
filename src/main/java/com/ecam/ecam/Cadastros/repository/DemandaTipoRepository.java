package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.DemandaTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandaTipoRepository extends JpaRepository<DemandaTipo, Integer> {
    @Query("SELECT dt FROM DemandaTipo dt WHERE dt.demanda.id = :idDemanda")
    List<DemandaTipo> findByDemandaId(@Param("idDemanda") Integer idDemanda);
}