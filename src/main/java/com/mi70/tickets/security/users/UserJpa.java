package com.mi70.tickets.security.users;

import com.mi70.tickets.model.entity.Usuario;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserJpa implements UserDetails {

    private Usuario usuario;
    public Collection<GrantedAuthority> authorities;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

    public UserJpa(Usuario usuario){
        this.usuario = usuario;
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(
                this.getUsuario().getNivelAcesso().toString()
        ));
        this.authorities = authorities;
        return authorities;
    }
}
