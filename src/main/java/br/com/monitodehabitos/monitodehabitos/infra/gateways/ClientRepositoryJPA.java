package br.com.monitodehabitos.monitodehabitos.infra.gateways;


import br.com.monitodehabitos.monitodehabitos.application.gateway.ClientRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntityRepository;

import java.util.Optional;
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
    public Client update(Client client) {
        return null;
    }

    @Override
    public Optional<Client> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(UUID id) {
        return null;
    }
}
