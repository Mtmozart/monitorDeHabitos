package br.com.monitodehabitos.monitodehabitos.domain;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;

import java.time.LocalDateTime;

public class FactoryClient {
    private Client client;

    public Client withAllParameters(String email, String password, String name, LocalDateTime createdAt, LocalDateTime updatedAt, Address address){
        this.client = new Client(email, password, name, createdAt, updatedAt, address, TypeUserEnum.CLIENT, true);
        return this.client;
    }

    public Client withoutCreatedatAndUpdatedatParameters(String email, String password, String name, Address address){
        this.client = new Client(email, password, name, LocalDateTime.now(), null, address, TypeUserEnum.CLIENT, true);
        return this.client;
    }
}
