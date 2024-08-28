package br.com.monitodehabitos.monitodehabitos.application.useCases.Client;

import br.com.monitodehabitos.monitodehabitos.application.gateway.ClientRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;

public class CreateClient {

    private final ClientRepository repository;

    public CreateClient(ClientRepository repository) {this.repository = repository;}

    public Client create(Client client) {
        return repository.save(client);
    }
}
