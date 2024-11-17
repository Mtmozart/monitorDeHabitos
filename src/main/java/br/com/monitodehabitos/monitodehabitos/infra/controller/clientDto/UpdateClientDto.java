package br.com.monitodehabitos.monitodehabitos.infra.controller.clientDto;

public record UpdateClientDto(
    String email,
    String password,
    String name,
    String cep,
    String  street,
    String city,
    String state,
    String neighborhood,
    String number,
    String complement
){}
