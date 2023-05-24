package com.mi70.tickets.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NivelAcesso {
    Administrador("Administrador"), //0
    Senior("Senior"), //1
    Pleno("Pleno"), //2
    Junior("Junior"), //3
    Usuario("Usuario"); //4

    String nivelAcesso;
}
