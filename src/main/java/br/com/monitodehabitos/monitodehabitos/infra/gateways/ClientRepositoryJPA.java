package br.com.monitodehabitos.monitodehabitos.infra.gateways;


import br.com.monitodehabitos.monitodehabitos.application.gateway.ClientRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserException;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntityRepository;

import java.util.UUID;

public class ClientRepositoryJPA implements ClientRepository {
    private final ClientEntityRepository clientEntityRepository;
    private final ClientEntityMapper clientEntityMapper;

    public ClientRepositoryJPA(ClientEntityRepository clientEntityRepository, ClientEntityMapper clientEntityMapper) {
        this.clientEntityRepository = clientEntityRepository;
        this.clientEntityMapper = clientEntityMapper;
    }

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = this.clientEntityMapper.toClientEntity(client);
        this.clientEntityRepository.save(clientEntity);
        return this.clientEntityMapper.toClientDomain(clientEntity);
    }

    @Override
    public Client update(String id, Client updateClient) throws UserException {
        ClientEntity clientEntity = this.clientEntityRepository.findById(id).orElse(null);
        if (clientEntity == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        Client client = this.clientEntityMapper.toClientDomain(clientEntity);
        client.updateClient(updateClient);
        ClientEntity clientEntityUpdated = this.clientEntityMapper.toClientEntityUpdate(id, client);
        this.clientEntityRepository.save(clientEntityUpdated);
        return client;
    }

    @Override
    public Client findById(String id) {
        ClientEntity clientEntity = this.clientEntityRepository.findById(id).orElse(null);
        if (clientEntity == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return this.clientEntityMapper.toClientDomain(clientEntity);
    }

    @Override
    public void delete(String id) {
        if (!clientEntityRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado para exclusão");
        }
        this.clientEntityRepository.deleteById(id);
    }

}
