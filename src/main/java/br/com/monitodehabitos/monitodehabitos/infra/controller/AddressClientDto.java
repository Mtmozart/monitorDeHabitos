package br.com.monitodehabitos.monitodehabitos.infra.controller;

public record AddressClientDto (
    String cep,
    String  street,
    String city,
    String state,
    String neighborhood,
    String number,
    String complement
){}
