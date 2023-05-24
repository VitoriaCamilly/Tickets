package com.mi70.tickets.controller;

import com.mi70.tickets.model.dto.UsuarioDTO;
import com.mi70.tickets.model.entity.Usuario;
import com.mi70.tickets.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController implements ControllerInterface<UsuarioDTO, Long>{

    private UsuarioService usuarioService;

    @Override
    public ResponseEntity<?> create(UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.create(usuarioDTO));
    }

    @Override
    public ResponseEntity<?> readOne(Long aLong) {
        return ResponseEntity.ok(usuarioService.readOne(aLong));
    }

    @Override
    public ResponseEntity<?> readAll() {
        return ResponseEntity.ok(usuarioService.readAll());
    }

    @Override
    public ResponseEntity<?> update(Long aLong, UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO,usuario);
        usuario.setId(aLong);
        usuarioService.update(usuario);
        return ResponseEntity.ok("Atualizado!");
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        usuarioService.delete(aLong);
        return ResponseEntity.ok("Deletado!");
    }
}
