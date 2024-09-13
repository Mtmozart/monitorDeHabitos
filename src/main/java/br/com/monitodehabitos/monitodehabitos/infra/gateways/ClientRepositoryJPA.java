package br.com.monitodehabitos.monitodehabitos.infra.gateways;


import br.com.monitodehabitos.monitodehabitos.application.gateway.ClientRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserExeption;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntityRepository;

import static br.com.monitodehabitos.monitodehabitos.infra.utils.Utilities.log;

public class ClientRepositoryJPA implements ClientRepository {
    private final ClientEntityRepository clientEntityRepository;
    private final ClientEntityMapper clientEntityMapper;

    public ClientRepositoryJPA(ClientEntityRepository clientEntityRepository, ClientEntityMapper clientEntityMapper) {
        this.clientEntityRepository = clientEntityRepository;
        this.clientEntityMapper = clientEntityMapper;
    }

    @Override
    public Client save(Client client) {
        log.info("Start the create client::ClientGateway");
        ClientEntity clientEntity = this.clientEntityMapper.toClientEntity(client);
        this.clientEntityRepository.save(clientEntity);
        log.info("End the create client::ClientGateway");
        return this.clientEntityMapper.toClientDomain(clientEntity);

    }

    @Override
    public Client update(Long id, Client updateClient) throws UserExeption {
        log.info("Start the update client::ClientGateway");
        ClientEntity clientEntity = this.clientEntityRepository.findById(id).orElse(null);
        if(clientEntity == null) {
            throw new RuntimeException("Usuário não encontrado");        }
        Client client = this.clientEntityMapper.toClientDomain(clientEntity);
        client.updateClient(updateClient);
        ClientEntity clientEntityUpdated = this.clientEntityMapper.toClientEntityUpdate(id, client);
        this.clientEntityRepository.save(clientEntityUpdated);
        log.info("End the update client::ClientGateway");
        return client;
    }

    @Override
    public Client findById(Long id) {
        log.info("Start the to find client::ClientGateway");
        ClientEntity clientEntity = this.clientEntityRepository.findById(id).orElse(null);
        if(clientEntity == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        log.info("End the to find client::ClientGateway");
        return this.clientEntityMapper.toClientDomain(clientEntity);
    }

    @Override
    public void delete(Long id) {
        log.info("Start the to delete client::ClientGateway");
        if (!clientEntityRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado para exclusão");
        }
        this.clientEntityRepository.deleteById(id);
        log.info("End the to delete client::ClientGateway");
    }
}
