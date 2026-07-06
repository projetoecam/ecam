package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReuniaoRepository extends JpaRepository<Reuniao, Integer> {
    // Busca customizada para facilitar a visualização do histórico de reuniões em uma comunidade
    List<Reuniao> findByComunidadeId(Integer idComunidade);
}