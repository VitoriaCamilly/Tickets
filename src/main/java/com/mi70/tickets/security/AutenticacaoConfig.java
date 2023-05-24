package com.mi70.tickets.security;

import com.mi70.tickets.security.service.JpaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class AutenticacaoConfig {

    private JpaService jpaService;


    // Configura as autorizações de acesso
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jpaService)
//                .passwordEncoder(new BCryptPasswordEncoder());
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("Security " + httpSecurity);
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/logout", "/login" ).permitAll()
                .requestMatchers(HttpMethod.GET, "/ticket", "/ticket/*", "/usuario").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuario")
                .hasAnyAuthority("Senior", "Administrador")
                .requestMatchers(HttpMethod.POST, "/ticket", "/comentario")
                .hasAnyAuthority("Usuario", "Junior", "Pleno", "Senior", "Administrador")
                .requestMatchers(HttpMethod.GET, "/comentario", "/comentario/*", "/usuario", "/usuario/*", "/endereco", "/endereco/*")
                .hasAnyAuthority("Usuario", "Junior", "Pleno", "Senior", "Administrador")
                .requestMatchers(HttpMethod.PUT, "/ticket", "/comentario")
                .hasAnyAuthority("Junior", "Pleno", "Senior", "Administrador")
                .requestMatchers(HttpMethod.POST, "/endereco")
                .hasAnyAuthority("Pleno", "Senior", "Administrador")
                .requestMatchers(HttpMethod.PUT, "/endereco")
                .hasAnyAuthority("Pleno", "Senior", "Administrador")
                .requestMatchers(HttpMethod.PUT, "/usuario")
                .hasAnyAuthority("Senior", "Administrador")
                .requestMatchers(HttpMethod.DELETE, "/ticket", "/comentario", "/endereco", "/usuario")
                .hasAnyAuthority("Administrador")
                .anyRequest().authenticated()
                .and().csrf().disable()
//                .cors().configurationSource(corsConfigurationSource())
                .logout().deleteCookies("token").permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoFiltro(new TokenUtils(), jpaService), UsernamePasswordAuthenticationFilter.class);
        System.out.println("Passou aqui");
        return httpSecurity.build();
    }
    //Serve pra poder fazer a injeção de dependência na controller
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration ac) throws Exception {
        return ac.getAuthenticationManager();
    }
}
