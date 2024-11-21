package br.com.monitodehabitos.monitodehabitos.application.useCases.Client;

import br.com.monitodehabitos.monitodehabitos.application.gateway.ClientRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserException;

import java.util.UUID;

public class UpdateClient {

    private final ClientRepository repository;

    public UpdateClient(ClientRepository repository) {this.repository = repository;}

    public Client update(String id, Client newClient) throws UserException {
        return repository.update(id, newClient);
    }
}
