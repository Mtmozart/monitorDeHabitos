package br.com.monitodehabitos.monitodehabitos.infra.controller;

public record CreateClientDto(
    String email,
    String password,
    String name,
    AddressClientDto addressClientDto
){}
