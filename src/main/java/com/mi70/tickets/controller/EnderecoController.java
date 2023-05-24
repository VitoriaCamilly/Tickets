package com.mi70.tickets.controller;

import com.mi70.tickets.model.dto.EnderecoDTO;
import com.mi70.tickets.model.entity.Endereco;
import com.mi70.tickets.service.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController implements ControllerInterface<EnderecoDTO, Long>{

    private EnderecoService comentarioService;

    @Override
    public ResponseEntity<?> create(EnderecoDTO enderecoDTO) {
        return ResponseEntity.ok(comentarioService.create(enderecoDTO));
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
    public ResponseEntity<?> update(Long aLong, EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO,endereco);
        endereco.setId(aLong);
        comentarioService.update(endereco);
        return ResponseEntity.ok("Atualizado!");
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        comentarioService.delete(aLong);
        return ResponseEntity.ok("Deletado!");
    }
}
