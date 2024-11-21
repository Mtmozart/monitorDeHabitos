package br.com.monitodehabitos.monitodehabitos.application.useCases.Client;

import br.com.monitodehabitos.monitodehabitos.application.gateway.ClientRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;

import java.util.UUID;

public class FindClient {
    private final ClientRepository repository;
    public FindClient(ClientRepository repository) {this.repository = repository;}
    public Client findClient(String id) {
        return repository.findById(id);
    }
}
