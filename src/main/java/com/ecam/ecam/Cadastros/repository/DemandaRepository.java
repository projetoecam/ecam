package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Integer> {
    // Você pode adicionar buscas customizadas futuramente, por exemplo:
    // List<Demanda> findByAnoAndStatus(Integer ano, String status);
}