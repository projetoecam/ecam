package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    
    // Retorna true se já existir no banco uma cidade com esse nome e essa UF (ignorando maiúsculas/minúsculas)
   //boolean existsByNomeIgnoreCaseAndUfIgnoreCase(String nome, String uf);
   int countByNomeIgnoreCaseAndUfIgnoreCase(String nome, String uf);
}
