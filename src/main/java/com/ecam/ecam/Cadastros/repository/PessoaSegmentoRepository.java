package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.PessoaSegmento;
import com.ecam.ecam.Cadastros.model.PessoaSegmentoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaSegmentoRepository extends JpaRepository<PessoaSegmento, PessoaSegmentoId> {
    
    // Buscas customizadas úteis
    List<PessoaSegmento> findByIdIdPessoa(Integer idPessoa);
    List<PessoaSegmento> findByIdIdSegmento(Integer idSegmento);
}