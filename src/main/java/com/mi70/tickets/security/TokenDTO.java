package com.mi70.tickets.security;

import lombok.Data;
import lombok.NonNull;


@Data
public class TokenDTO {
    @NonNull
    private String tipo;
    @NonNull
    private String token;
}
