
package com.mi70.tickets.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    @ManyToOne
    private Endereco endereco;
    private Double salario;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private NivelAcesso nivelAcesso;
}
