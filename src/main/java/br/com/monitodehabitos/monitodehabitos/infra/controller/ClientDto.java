package br.com.monitodehabitos.monitodehabitos.infra.controller;

import java.time.LocalDateTime;

public record ClientDto (
    String email,
    String password,
    String name,
    AddressClientDto addressClientDto
){}
