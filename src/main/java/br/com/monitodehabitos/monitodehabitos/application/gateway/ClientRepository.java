package br.com.monitodehabitos.monitodehabitos.application.gateway;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserException;

public interface ClientRepository {

    Client save(Client client);
    Client update(String id, Client newClient) throws UserException;
    Client findById(String id);
    void delete(String id);

}
