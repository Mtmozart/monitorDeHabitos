package br.com.monitodehabitos.monitodehabitos.application.gateway;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {

    Client save(Client client);
    Client update(Client client);
    Optional<Client> findById(UUID id);
    Boolean delete(UUID id);
}
