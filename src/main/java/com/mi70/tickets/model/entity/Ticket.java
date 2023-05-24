package com.mi70.tickets.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @ManyToOne
    private Usuario emissor;
    @ManyToOne
    private Usuario responsavel;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String titulo;
    private String descricao;
    @OneToMany(mappedBy = "ticket")
    private List<Comentario> comentarios;
}
