package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.MacroRegiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MacroRegiaoRepository extends JpaRepository<MacroRegiao, Integer> {
}