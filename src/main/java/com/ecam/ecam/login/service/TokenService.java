package com.ecam.ecam.login.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ecam.ecam.login.model.Permissao;
import com.ecam.ecam.login.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Value("${api.security.token.secret:minha_chave_secreta_padrao}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Agora percorremos dinamicamente todos os perfis e extraímos os Nomes das Permissões
            List<String> permissoesStr = usuario.getPerfis().stream()
                    .flatMap(perfil -> perfil.getPermissoes().stream())
                    .map(Permissao::getNome)
                    .distinct() // Evita permissões duplicadas no token
                    .collect(Collectors.toList());

            return JWT.create()
                    .withIssuer("ecam-api")
                    .withSubject(usuario.getLogin_usuario())
                    .withClaim("codigoSessao", usuario.getCodigo_sessao())
                    .withClaim("permissoes", permissoesStr) // Injeta a lista dinâmica de permissões no Token
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public DecodedJWT validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ecam-api")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusMinutes(24).toInstant(ZoneOffset.of("-03:00"));
    }
}