package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer> {
    // Busca customizada para facilitar a visualização de histórico de uma pessoa
    List<Atendimento> findByPessoaId(Integer idPessoa);
}