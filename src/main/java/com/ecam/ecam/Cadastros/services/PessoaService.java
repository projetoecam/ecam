package com.ecam.ecam.Cadastros.services;

import com.ecam.ecam.Cadastros.dto.PessoaDTO;
import com.ecam.ecam.Cadastros.model.Comunidade;
import com.ecam.ecam.Cadastros.model.Pessoa;
import com.ecam.ecam.login.model.Usuario;
import com.ecam.ecam.Cadastros.repository.PessoaRepository;
import com.ecam.ecam.Cadastros.repository.ComunidadeRepository;
import com.ecam.ecam.login.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private ComunidadeRepository comunidadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PessoaDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public PessoaDTO buscarPorId(Integer id) {
        Pessoa entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));
        return converterParaDTO(entidade);
    }

    @Transactional
    public PessoaDTO salvar(PessoaDTO dto) {
        Pessoa entidade = converterParaEntidade(dto);

        if (entidade.getTituloEleitor() != null &&
                (entidade.getTituloEleitor().equals("NAO_INFORMADO") || entidade.getTituloEleitor().isEmpty())) {
            entidade.setTituloEleitor(null);
        }

        if (entidade.getDataCadastro() == null) {
            entidade.setDataCadastro(LocalDateTime.now());
        }

        entidade = repository.save(entidade);
        return converterParaDTO(entidade);
    }

    @Transactional
    public PessoaDTO atualizar(Integer id, PessoaDTO dto) {
        Pessoa entidadeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));

        entidadeExistente.setNomeCompleto(dto.getNomeCompleto());
        entidadeExistente.setCpf(dto.getCpf());
        entidadeExistente.setTituloEleitor(dto.getTituloEleitor());
        entidadeExistente.setNomeMae(dto.getNomeMae());
        entidadeExistente.setDataNascimento(dto.getDataNascimento());
        entidadeExistente.setTelefone(dto.getTelefone());
        entidadeExistente.setWhatsapp(dto.getWhatsapp());
        entidadeExistente.setEnderecoCompleto(dto.getEnderecoCompleto());
        entidadeExistente.setCep(dto.getCep());
        entidadeExistente.setOrigemCadastro(dto.getOrigemCadastro());
        entidadeExistente.setStatus(dto.getStatus());
        entidadeExistente.setObservacoes(dto.getObservacoes());

        if (dto.getIdComunidade() != null) {
            Comunidade comunidade = new Comunidade();
            comunidade.setId(dto.getIdComunidade());
            entidadeExistente.setComunidade(comunidade);
        }

        if (dto.getIdLiderResponsavel() != null) {
            Pessoa lider = new Pessoa();
            lider.setId(dto.getIdLiderResponsavel());
            entidadeExistente.setLiderResponsavel(lider);
        } else {
            entidadeExistente.setLiderResponsavel(null);
        }

        if (dto.getIdLiderRegional() != null) {
            Pessoa liderRegional = new Pessoa();
            liderRegional.setId(dto.getIdLiderRegional());
            entidadeExistente.setLiderRegional(liderRegional);
        } else {
            entidadeExistente.setLiderRegional(null);
        }

        if (dto.getIdUsuarioCadastro() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.getIdUsuarioCadastro());
            entidadeExistente.setUsuarioCadastro(usuario);
        }

        return converterParaDTO(repository.save(entidadeExistente));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    private PessoaDTO converterParaDTO(Pessoa entidade) {
        return PessoaDTO.builder()
                .id(entidade.getId())
                .nomeCompleto(entidade.getNomeCompleto())
                .cpf(entidade.getCpf())
                .tituloEleitor(entidade.getTituloEleitor())
                .nomeMae(entidade.getNomeMae())
                .dataNascimento(entidade.getDataNascimento())
                .telefone(entidade.getTelefone())
                .whatsapp(entidade.getWhatsapp())
                .idComunidade(entidade.getComunidade() != null ? entidade.getComunidade().getId() : null)
                .enderecoCompleto(entidade.getEnderecoCompleto())
                .cep(entidade.getCep())
                .origemCadastro(entidade.getOrigemCadastro())
                .idLiderResponsavel(
                        entidade.getLiderResponsavel() != null ? entidade.getLiderResponsavel().getId() : null)
                .idLiderRegional(entidade.getLiderRegional() != null ? entidade.getLiderRegional().getId() : null)
                .status(entidade.getStatus())
                .observacoes(entidade.getObservacoes())
                .idUsuarioCadastro(entidade.getUsuarioCadastro() != null ? entidade.getUsuarioCadastro().getId() : null)
                .dataCadastro(entidade.getDataCadastro())
                .build();
    }

    private Pessoa converterParaEntidade(PessoaDTO dto) {
        Pessoa entidade = Pessoa.builder()
                .id(dto.getId())
                .nomeCompleto(dto.getNomeCompleto())
                .cpf(dto.getCpf())
                .tituloEleitor(dto.getTituloEleitor())
                .nomeMae(dto.getNomeMae())
                .dataNascimento(dto.getDataNascimento())
                .telefone(dto.getTelefone())
                .whatsapp(dto.getWhatsapp())
                .enderecoCompleto(dto.getEnderecoCompleto())
                .cep(dto.getCep())
                .origemCadastro(dto.getOrigemCadastro())
                .status(dto.getStatus())
                .observacoes(dto.getObservacoes())
                .dataCadastro(dto.getDataCadastro())
                .build();

        if (dto.getIdComunidade() != null) {
            Comunidade comunidade = new Comunidade();
            comunidade.setId(dto.getIdComunidade());
            entidade.setComunidade(comunidade);
        }

        if (dto.getIdLiderResponsavel() != null) {
            Pessoa lider = new Pessoa();
            lider.setId(dto.getIdLiderResponsavel());
            entidade.setLiderResponsavel(lider);
        }

        if (dto.getIdLiderRegional() != null) {
            Pessoa liderRegional = new Pessoa();
            liderRegional.setId(dto.getIdLiderRegional());
            entidade.setLiderRegional(liderRegional);
        }

        if (dto.getIdUsuarioCadastro() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.getIdUsuarioCadastro());
            entidade.setUsuarioCadastro(usuario);
        }

        return entidade;
    }
}