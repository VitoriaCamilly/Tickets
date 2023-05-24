package com.mi70.tickets.service;

import com.mi70.tickets.model.dto.TicketDTO;
import com.mi70.tickets.model.entity.Ticket;
import com.mi70.tickets.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@AllArgsConstructor
public class TicketService implements ServiceInterface<Ticket, TicketDTO, Long> {

    private TicketRepository ticketRepository;

    @Override
    public Ticket create(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketDTO, ticket);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket readOne(Long aLong) {
        return ticketRepository.findById(aLong).get();
    }

    @Override
    public List<Ticket> readAll() {
        return ticketRepository.findAll();
    }

    @Override
    public void update(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long aLong) {
        ticketRepository.deleteById(aLong);
    }
}
