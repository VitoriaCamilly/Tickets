package com.mi70.tickets.controller;

import com.mi70.tickets.model.dto.TicketDTO;
import com.mi70.tickets.model.entity.Ticket;
import com.mi70.tickets.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController implements ControllerInterface<TicketDTO, Long>{

    private TicketService ticketService;

    @Override
    public ResponseEntity<?> create(TicketDTO ticketDTO) {
        return ResponseEntity.ok(ticketService.create(ticketDTO));
    }

    @Override
    public ResponseEntity<?> readOne(Long aLong) {
        return ResponseEntity.ok(ticketService.readOne(aLong));
    }

    @Override
    public ResponseEntity<?> readAll() {
        return ResponseEntity.ok(ticketService.readAll());
    }

    @Override
    public ResponseEntity<?> update(Long aLong, TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketDTO,ticket);
        ticket.setCodigo(aLong);
        ticketService.update(ticket);
        return ResponseEntity.ok("Atualizado!");
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        ticketService.delete(aLong);
        return ResponseEntity.ok("Deletado!");
    }
}
