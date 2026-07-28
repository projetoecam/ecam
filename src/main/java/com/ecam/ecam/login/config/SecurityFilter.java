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
import java.util.ArrayList;
import java.util.List;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = recuperarToken(request);

        if (token != null) {
            DecodedJWT decodedJWT = tokenService.validarToken(token);

            if (decodedJWT != null) {
                String login = decodedJWT.getSubject();
                String tokenSessao = decodedJWT.getClaim("codigoSessao").asString();
                List<String> permissoes = decodedJWT.getClaim("permissoes").asList(String.class);

                Optional<Usuario> usuarioOpt = usuarioRepository.buscarPorLogin(login);

                if (usuarioOpt.isPresent()) {
                    Usuario usuario = usuarioOpt.get();

                    if (tokenSessao.equals(usuario.getCodigo_sessao())) {
                        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        
                        // Agora percorremos os perfis N-N do usuário para registar as Roles
                        usuario.getPerfis().forEach(perfil -> {
                            String roleNormalizada = perfil.getNome().toUpperCase().replace(" ", "_");
                            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleNormalizada));
                        });
                        
                        // Adiciona as permissões específicas recuperadas do Token
                        if (permissoes != null) {
                            permissoes.forEach(p -> authorities.add(new SimpleGrantedAuthority(p)));
                        }

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
        if (authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }
}