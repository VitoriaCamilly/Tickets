package com.mi70.tickets.security;


import com.mi70.tickets.security.service.JpaService;
import com.mi70.tickets.security.users.UserJpa;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private TokenUtils tokenUtils = new TokenUtils();
    @Autowired
    private JpaService jpaService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/verify-token")
    public ResponseEntity<Object> autenticacao(HttpServletRequest request) {
        Boolean valido = false;
        UserDetails usuario = null;
        try{
            String token = tokenUtils.buscarCookie(request);
            valido = tokenUtils.validarToken(token);
            if(valido){
                String usuarioUsername = tokenUtils.getUsuarioUsername(token);
                usuario = jpaService.loadUserByUsername(usuarioUsername);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(valido);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // Lógica de logout, como invalidar a sessão e remover o cookie de autenticação
        request.getSession().invalidate();
        Cookie cookie = new Cookie("jwt", null);
        cookie.setPath("/");
        cookie.setMaxAge(60);
        response.addCookie(cookie);
        // Redirecionar para a página de login ou retornar uma resposta personalizada
        return ResponseEntity.ok("Logout realizado com sucesso");
    }



    @PostMapping
    public ResponseEntity<Object> autenticacao(
            @RequestBody @Valid UsuarioLoginDTO usuarioDTO,
            HttpServletResponse response) {
        System.out.println("Iniciou");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        usuarioDTO.getEmail(),usuarioDTO.getSenha());

        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        if (authentication.isAuthenticated()) {
            response.addCookie(tokenUtils.gerarCookie(authentication));
            UserJpa userJpa = (UserJpa) authentication.getPrincipal();
            System.out.println(userJpa.toString());
            return ResponseEntity.ok(userJpa.getUsuario());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}