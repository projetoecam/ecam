package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Encaminhamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EncaminhamentoRepository extends JpaRepository<Encaminhamento, Integer> {
    // Busca customizada útil: Listar todos os encaminhamentos de uma demanda específica
    List<Encaminhamento> findByDemandaId(Integer idDemanda);
}