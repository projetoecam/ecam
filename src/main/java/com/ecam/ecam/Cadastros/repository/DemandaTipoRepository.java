package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.DemandaTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DemandaTipoRepository extends JpaRepository<DemandaTipo, Integer> {
    @Query("SELECT dt FROM DemandaTipo dt WHERE dt.demanda.id = :idDemanda")
    List<DemandaTipo> findByDemandaId(@Param("idDemanda") Integer idDemanda);
}