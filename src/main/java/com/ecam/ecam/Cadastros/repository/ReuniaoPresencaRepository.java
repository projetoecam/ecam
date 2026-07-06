package com.ecam.ecam.Cadastros.repository;

import com.ecam.ecam.Cadastros.model.ReuniaoPresenca;
import com.ecam.ecam.Cadastros.model.ReuniaoPresencaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReuniaoPresencaRepository extends JpaRepository<ReuniaoPresenca, ReuniaoPresencaId> {
    
    // Lista todas as presenças de uma determinada reunião
    List<ReuniaoPresenca> findByIdIdReuniao(Integer idReuniao);
    
    // Lista todas as reuniões que uma determinada pessoa participou
    List<ReuniaoPresenca> findByIdIdPessoa(Integer idPessoa);
}