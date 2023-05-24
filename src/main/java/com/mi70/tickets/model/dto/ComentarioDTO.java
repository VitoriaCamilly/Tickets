package com.mi70.tickets.model.dto;

import com.mi70.tickets.model.entity.Ticket;
import com.mi70.tickets.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {
    private Ticket ticket;
    private Usuario emissor;
    private String comentario;
}
