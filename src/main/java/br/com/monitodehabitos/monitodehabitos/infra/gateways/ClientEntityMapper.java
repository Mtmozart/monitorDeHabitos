package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.AddressEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntity;


public class ClientEntityMapper {

    public Address toAddressDomain(AddressEntity addressEntity){
        return new Address(
                addressEntity.getCep(),
                addressEntity.getStreet(),
                addressEntity.getCity(),
                addressEntity.getState(),
                addressEntity.getNeighborhood(),
                addressEntity.getNumber(),
                addressEntity.getComplement()
        );
    }

    public AddressEntity toAddressEntity(Address address){
        return new AddressEntity(
                address.getCep(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getNeighborhood(),
                address.getNumber(),
                address.getComplement()
        );
    }

    public Client toClientDomain(ClientEntity clientEntity) {
        return new Client(
                clientEntity.getEmail(),
                clientEntity.getPassword(),
                clientEntity.getName(),
                clientEntity.getCreatedAt(),
                clientEntity.getUpdatedAt(),
                toAddressDomain(clientEntity.getAddressEntity()),
                clientEntity.getTypeUser(),
                clientEntity.getClient()
        );
    }
    public ClientEntity toClientEntity(Client client) {
        return new ClientEntity(
                null,
                client.getEmail(),
                client.getPassword(),
                client.getName(),
                client.getTypeUserEnum(),
                client.getClient(),
                client.getCreatedAt(),
                client.getUpdatedAt(),
                toAddressEntity(client.getAddress())
        );
    }

    public ClientEntity toClientEntityUpdate(Long id, Client client) {
        return new ClientEntity(
                id,
                client.getEmail(),
                client.getPassword(),
                client.getName(),
                client.getTypeUserEnum(),
                client.getClient(),
                client.getCreatedAt(),
                client.getUpdatedAt(),
                toAddressEntity(client.getAddress())
        );
    }
}
