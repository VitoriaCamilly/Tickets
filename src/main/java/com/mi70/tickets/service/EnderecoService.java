package com.mi70.tickets.service;

import com.mi70.tickets.model.dto.EnderecoDTO;
import com.mi70.tickets.model.entity.Endereco;
import com.mi70.tickets.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@AllArgsConstructor
public class EnderecoService implements ServiceInterface<Endereco, EnderecoDTO, Long> {

    private EnderecoRepository enderecoRepository;

    @Override
    public Endereco create(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);
        return enderecoRepository.save(endereco);
    }

    @Override
    public Endereco readOne(Long aLong) {
        return enderecoRepository.findById(aLong).get();
    }

    @Override
    public List<Endereco> readAll() {
        return enderecoRepository.findAll();
    }

    @Override
    public void update(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    @Override
    public void delete(Long aLong) {
        enderecoRepository.deleteById(aLong);
    }
}