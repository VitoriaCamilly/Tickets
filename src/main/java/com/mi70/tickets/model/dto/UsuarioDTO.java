package com.mi70.tickets.model.dto;

import com.mi70.tickets.model.entity.Endereco;
import com.mi70.tickets.model.entity.NivelAcesso;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private Endereco endereco;
    private Double salario;
    private NivelAcesso nivelAcesso;
}
