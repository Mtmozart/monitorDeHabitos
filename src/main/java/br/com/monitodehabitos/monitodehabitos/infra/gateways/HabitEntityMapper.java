package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Progress;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.AddressEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ProgressEntity;

import java.util.List;
import java.util.stream.Collectors;

public class HabitEntityMapper {

    public HabitEntity toHabitEntityCreate(Habit habit) {
        return null;
    }

    public HabitEntity toHabitEntityWithAllParamentrs(Habit habit) {
        return null;
    }

    public Habit toHabitDomainWithAllParameters(HabitEntity habitEntity) {
        return null;
    }

    public Client toClientDomain(ClientEntity clientEntity) {
        if (clientEntity == null) return null;

        return null;
    }

    public Address toAddressDomain(AddressEntity addressEntity) {
        if (addressEntity == null) return null;

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

    public ClientEntity toClientEntity(Client client) {
        return new ClientEntity(
                client.getId(),
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

    public AddressEntity toAddressEntity(Address address) {
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

    public ProgressEntity toProgressEntity(Progress progress) {
        return null;
    }

    public List<ProgressEntity> toProgressEntityListWithAllParamenters(Habit habit) {
        return  null;
    }

    public ProgressEntity toProgressEntityWithAllParamenters(Progress progress, Habit habit){
        return null;
    }

    public List<ProgressEntity> toProgressEntityMapper(Habit habit) {
        return null;
    }

    public Progress toProgressDomain(ProgressEntity progressEntity) {
        return null;
    }

    public List<Progress> toProgressDomainMapper(List<ProgressEntity> progressEntities) {
        return progressEntities.stream()
                .map(this::toProgressDomain)
                .collect(Collectors.toList());
    }
}
