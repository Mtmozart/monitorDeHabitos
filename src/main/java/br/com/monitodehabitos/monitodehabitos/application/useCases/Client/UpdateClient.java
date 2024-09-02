package br.com.monitodehabitos.monitodehabitos.application.useCases.Client;

import br.com.monitodehabitos.monitodehabitos.application.gateway.ClientRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;

public class UpdateClient {

    private final ClientRepository repository;

    public UpdateClient(ClientRepository repository) {this.repository = repository;}

    public Client update(Long id, Client newClient) {
        return repository.update(id, newClient);
    }
}
