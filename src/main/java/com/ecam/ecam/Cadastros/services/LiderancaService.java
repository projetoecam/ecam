package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.LiderancaDTO;
import com.ecam.ecam.Cadastros.model.Lideranca;
import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.Cadastros.repository.LiderancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiderancaService {

    @Autowired
    private LiderancaRepository repository;

    public List<LiderancaDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public LiderancaDTO buscarPorId(Integer id) {
        Lideranca entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liderança não encontrada!"));
        return converterParaDTO(entidade);
    }

    public LiderancaDTO salvar(LiderancaDTO dto) {
        if (dto.getIdPessoa() == null) {
            throw new RuntimeException("O ID da Pessoa é obrigatório para cadastrar uma Liderança!");
        }
        
        Lideranca entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    public LiderancaDTO atualizar(Integer id, LiderancaDTO dto) {
        Lideranca entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liderança não encontrada!"));

        entidadeExistente.setTipoLideranca(dto.getTipoLideranca());
        entidadeExistente.setClassificacao(dto.getClassificacao());
        entidadeExistente.setQtdPessoasMobiliza(dto.getQtdPessoasMobiliza());
        entidadeExistente.setHistoricoPolitico(dto.getHistoricoPolitico());

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    // --- Métodos de Conversão ---

    private LiderancaDTO converterParaDTO(Lideranca entidade) {
        return LiderancaDTO.builder()
                .idPessoa(entidade.getId())
                .tipoLideranca(entidade.getTipoLideranca())
                .classificacao(entidade.getClassificacao())
                .qtdPessoasMobiliza(entidade.getQtdPessoasMobiliza())
                .historicoPolitico(entidade.getHistoricoPolitico())
                .build();
    }

    private Lideranca converterParaEntidade(LiderancaDTO dto) {
        Lideranca entidade = Lideranca.builder()
                .tipoLideranca(dto.getTipoLideranca())
                .classificacao(dto.getClassificacao())
                .qtdPessoasMobiliza(dto.getQtdPessoasMobiliza())
                .historicoPolitico(dto.getHistoricoPolitico())
                .build();

        if (dto.getIdPessoa() != null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(dto.getIdPessoa());
            entidade.setPessoa(pessoa);
            // Com o @MapsId, definir a pessoa é suficiente para mapear a chave primária
        }

        return entidade;
    }
}