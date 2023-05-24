package com.mi70.tickets.service;

import com.mi70.tickets.model.dto.ComentarioDTO;
import com.mi70.tickets.model.entity.Comentario;
import com.mi70.tickets.repository.ComentarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@AllArgsConstructor
public class ComentarioService implements ServiceInterface<Comentario, ComentarioDTO, Long> {

    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario create(ComentarioDTO comentarioDTO) {
        Comentario comentario = new Comentario();
        BeanUtils.copyProperties(comentarioDTO, comentario);
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario readOne(Long aLong) {
        return comentarioRepository.findById(aLong).get();
    }

    @Override
    public List<Comentario> readAll() {
        return comentarioRepository.findAll();
    }

    @Override
    public void update(Comentario comentario) {
        comentarioRepository.save(comentario);
    }

    @Override
    public void delete(Long aLong) {
        comentarioRepository.deleteById(aLong);
    }
}
