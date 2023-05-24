package com.mi70.tickets.controller;

import com.mi70.tickets.model.dto.ComentarioDTO;
import com.mi70.tickets.model.entity.Comentario;
import com.mi70.tickets.service.ComentarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/comentario")
public class ComentarioController implements ControllerInterface<ComentarioDTO, Long>{

    private ComentarioService comentarioService;

    @Override
    public ResponseEntity<?> create(ComentarioDTO ticketDTO) {
        return ResponseEntity.ok(comentarioService.create(ticketDTO));
    }

    @Override
    public ResponseEntity<?> readOne(Long aLong) {
        return ResponseEntity.ok(comentarioService.readOne(aLong));
    }

    @Override
    public ResponseEntity<?> readAll() {
        return ResponseEntity.ok(comentarioService.readAll());
    }

    @Override
    public ResponseEntity<?> update(Long aLong, ComentarioDTO ticketDTO) {
        Comentario comentario = new Comentario();
        BeanUtils.copyProperties(ticketDTO,comentario);
        comentario.setId(aLong);
        comentarioService.update(comentario);
        return ResponseEntity.ok("Atualizado!");
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        comentarioService.delete(aLong);
        return ResponseEntity.ok("Deletado!");
    }
}
