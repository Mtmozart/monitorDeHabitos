package br.com.monitodehabitos.monitodehabitos.infra.controller.clientDto;

public record UpdateAddressClientDto(
    String cep,
    String  street,
    String city,
    String state,
    String neighborhood,
    String number,
    String complement
){}
