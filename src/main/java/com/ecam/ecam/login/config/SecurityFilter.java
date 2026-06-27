package com.ecam.ecam.login.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ecam.ecam.login.model.Usuario;
import com.ecam.ecam.login.repository.UsuarioRepository;
import com.ecam.ecam.login.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recuperarToken(request);

        if (token != null) {
            DecodedJWT decodedJWT = tokenService.validarToken(token);
            
            if (decodedJWT != null) {
                String login = decodedJWT.getSubject();
                String tokenSessao = decodedJWT.getClaim("codigoSessao").asString();

                Optional<Usuario> usuarioOpt = usuarioRepository.buscarPorLogin(login);

                if (usuarioOpt.isPresent()) {
                    Usuario usuario = usuarioOpt.get();

                    if (tokenSessao.equals(usuario.getCodigo_sessao())) {
                        var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil()));
                        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}