package com.mi70.tickets.security;

import com.mi70.tickets.security.service.JpaService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class AutenticacaoFiltro extends OncePerRequestFilter {

    private TokenUtils tokenUtils;

    private JpaService jpaService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/login") ||
                request.getRequestURI().equals("/logout") ||
                request.getRequestURI().startsWith("/usuario") ||
                request.getRequestURI().equals("/usuario")) {
            System.out.println("Entrou? ");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("Saiu");
        String token = tokenUtils.buscarCookie(request);
//        System.out.println("Passou token");
        Boolean valido = tokenUtils.validarToken(token);
//        System.out.println(valido);
        if (valido) {
            System.out.println("Valido");
//            System.out.println("Entrou token valido");
            String usuarioUsername = tokenUtils.getUsuarioUsername(token);
            UserDetails usuario = jpaService.loadUserByUsername(usuarioUsername);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(usuario.getUsername(),
                            null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(
                    usernamePasswordAuthenticationToken
            );
            System.out.println("Foi pro filtro");
            filterChain.doFilter(request, response);
            return;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
