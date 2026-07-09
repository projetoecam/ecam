package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ReuniaoRepository extends JpaRepository<Reuniao, Integer> {
    // Busca customizada para facilitar a visualização do histórico de reuniões em uma comunidade
    List<Reuniao> findByComunidadeId(Integer idComunidade);
}