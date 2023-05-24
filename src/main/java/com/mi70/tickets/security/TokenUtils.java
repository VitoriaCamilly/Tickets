package com.mi70.tickets.security;


import com.mi70.tickets.security.users.UserJpa;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.util.WebUtils;

import java.util.Date;

public class TokenUtils {
    private final String senhaForte = "c127a7b6adb013a5ff879ae71afa62afa4b4ceb72afaa54711dbcde67b6dc325";
//    private UsuarioServic usuarioService;
    public String gerarToken(Authentication authentication) {
        UserJpa userJpa = (UserJpa) authentication.getPrincipal();
        return Jwts.builder()
                .setIssuer("CliniPet")
                .setSubject(userJpa.getUsuario().getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 27000000))
                .signWith(SignatureAlgorithm.HS256, senhaForte)
                .compact();
    }


    public Boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(senhaForte).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Cookie gerarCookie(Authentication authentication){
        Cookie cookie = new Cookie("token", gerarToken(authentication));
        cookie.setPath("/");
        cookie.setMaxAge(2700);
        return cookie;
    }

    public String getUsuarioUsername(String token) {
        String usuario = Jwts.parser()
                .setSigningKey(senhaForte)
                .parseClaimsJws(token)
                .getBody().getSubject();

        return usuario;
    }

    public String getUsuarioUsernameByRequest(HttpServletRequest request){
        String token = buscarCookie(request);
        return getUsuarioUsername(token);
    }
    public String buscarCookie(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request,"token");
        if(cookie != null){
            return cookie.getValue();
        }
        throw new RuntimeException("Cookie não encontrado");
    }
}
