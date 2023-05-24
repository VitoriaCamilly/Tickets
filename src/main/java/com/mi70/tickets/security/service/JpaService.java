package com.mi70.tickets.security.service;

import com.mi70.tickets.model.entity.Usuario;
import com.mi70.tickets.repository.UsuarioRepository;
import com.mi70.tickets.security.users.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(
            String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional;

        usuarioOptional = usuarioRepository.findByEmail(username);

        if (usuarioOptional.isPresent()) {
            return new UserJpa(usuarioOptional.get());
        }
        throw new UsernameNotFoundException("Usuário não encontrado!");
    }
}
