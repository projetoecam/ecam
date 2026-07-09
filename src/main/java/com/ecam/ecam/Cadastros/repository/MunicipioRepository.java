package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    // Você pode adicionar buscas customizadas depois, como buscar por UF, por exemplo:
    // List<Municipio> findByUf(String uf);
}