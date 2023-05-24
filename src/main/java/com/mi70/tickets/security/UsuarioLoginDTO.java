package com.mi70.tickets.security;

import lombok.Data;
import lombok.NonNull;

@Data
public class UsuarioLoginDTO {
    @NonNull
    private String email;
    @NonNull
    private String senha;
}
