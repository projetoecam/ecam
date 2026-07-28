package com.ecam.ecam.Cadastros.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ProblemDetail handleResponseStatusException(ResponseStatusException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatusCode(), ex.getReason());
        problemDetail.setTitle("Erro de Negócio / Recurso não encontrado");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Usuário não possui permissão para realizar a operação.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(RecursoJaCadastradoException.class)
    public ResponseEntity<ErroPadrao> handleRecursoJaCadastrado(RecursoJaCadastradoException ex) {
        ErroPadrao erro = new ErroPadrao(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflito de Dados",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroPadrao> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErroPadrao erro = new ErroPadrao(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflito de Integridade",
                "Não é possível excluir este registro, pois ele possui vínculos com outros dados do sistema (ex: Demandas cadastradas)."
        );
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    public record ErroPadrao(
            LocalDateTime timestamp,
            Integer status,
            String erro,
            String mensagem
    ) {}
}