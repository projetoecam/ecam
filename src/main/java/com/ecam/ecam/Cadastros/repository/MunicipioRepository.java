package com.ecam.ecam.Cadastros.repository;



import com.ecam.ecam.Cadastros.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    // O Spring implementa automaticamente
    boolean existsByNomeAndUf(String nome, String uf);
}