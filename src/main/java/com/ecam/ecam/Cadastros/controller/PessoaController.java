package com.ecam.ecam.Cadastros.controller;
import com.ecam.ecam.Cadastros.model.Pessoas;
import com.ecam.ecam.Cadastros.repository.PessoasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoasRepository repository;

    
    @GetMapping
    public List<Pessoas> listarTodas() {
        return repository.findAll();
    }

   
    @PostMapping
    public Pessoas criarPessoa(@RequestBody Pessoas novaPessoa) {
        return repository.save(novaPessoa);
    }
}