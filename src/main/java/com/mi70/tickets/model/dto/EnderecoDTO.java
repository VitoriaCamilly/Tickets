package com.mi70.tickets.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private Integer numero;
    private Long cep;
}
