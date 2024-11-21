package br.com.monitodehabitos.monitodehabitos.application.useCases.Client;

import br.com.monitodehabitos.monitodehabitos.application.gateway.ClientRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;

import java.util.UUID;

public class DeleteClient {
    private final ClientRepository repository;

    public DeleteClient(ClientRepository repository) {
        this.repository = repository;
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
