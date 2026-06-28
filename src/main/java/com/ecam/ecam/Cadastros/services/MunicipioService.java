package com.ecam.ecam.Cadastros.services;



import com.ecam.ecam.Cadastros.dto.MunicipioRequest;
import com.ecam.ecam.Cadastros.dto.MunicipioResponse;
import com.ecam.ecam.Cadastros.model.Municipio;
import com.ecam.ecam.Cadastros.repository.MunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MunicipioService {

    private final MunicipioRepository repository;

    @Transactional
    public MunicipioResponse criar(MunicipioRequest request) {
        if (repository.existsByNomeAndUf(request.nome(), request.uf())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Município já cadastrado nesta UF.");
        }

        Municipio municipio = Municipio.builder()
                .nome(request.nome())
                .uf(request.uf() != null ? request.uf().toUpperCase() : "PE")
                .build();

        municipio = repository.save(municipio);
        return MunicipioResponse.fromEntity(municipio);
    }

    @Transactional(readOnly = true)
    public MunicipioResponse buscarPorId(Integer id) {
        return repository.findById(id)
                .map(MunicipioResponse::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Município não encontrado."));
    }

    @Transactional(readOnly = true)
    public Page<MunicipioResponse> listarTodos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(MunicipioResponse::fromEntity);
    }
}