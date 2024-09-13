package br.com.monitodehabitos.monitodehabitos.application.gateway;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserExeption;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {

    Client save(Client client);
    Client update(Long id, Client newClient) throws UserExeption;
    Client findById(Long id);
    void delete(Long id);
}
