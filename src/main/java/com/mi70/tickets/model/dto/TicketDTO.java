package com.mi70.tickets.model.dto;


import com.mi70.tickets.model.entity.Status;
import com.mi70.tickets.model.entity.Usuario;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Usuario emissor;
    private Usuario responsavel;
    private Status status;
    private String titulo;
    private String descricao;
}
