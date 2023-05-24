package com.mi70.tickets.service;

import com.mi70.tickets.model.dto.UsuarioDTO;
import com.mi70.tickets.model.entity.Usuario;
import com.mi70.tickets.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@AllArgsConstructor
public class UsuarioService implements ServiceInterface<Usuario, UsuarioDTO, Long> {

    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario create(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario readOne(Long aLong) {
        return usuarioRepository.findById(aLong).get();
    }

    @Override
    public List<Usuario> readAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public void update(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long aLong) {
        usuarioRepository.deleteById(aLong);
    }
}
