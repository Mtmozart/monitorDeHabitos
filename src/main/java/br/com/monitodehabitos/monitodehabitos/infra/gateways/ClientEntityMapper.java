package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.AddressEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.WeekEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


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

    public ClientEntity toClientEntityUpdate(String id, Client client) {
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

    public Client toClientDomain(ClientEntity clientEntity) {
        return  new Client(
                clientEntity.getId(),
                clientEntity.getEmail(),
                clientEntity.getPassword(),
                clientEntity.getName(),
                clientEntity.getCreatedAt(),
                clientEntity.getUpdatedAt(),
                toAddressDomain(clientEntity.getAddressEntity()),
                clientEntity.getTypeUser(),
                clientEntity.getClient(),
                convertHabitsDomain(clientEntity.getHabits())
        )

                ;
    }
    private List<Habit> convertHabitsDomain(List<HabitEntity> habitEntities) {
        return habitEntities.stream()
                .map(this::toHabitDomain)
                .collect(Collectors.toList());
    }

    private Habit toHabitDomain(HabitEntity habitEntity) {
        return new Habit(
                habitEntity.getId(),
                habitEntity.getDescription(),
                habitEntity.getDone(),
                habitEntity.getStart(),
                habitEntity.getEnd(),
                null,
                null
        );
    }

    private Set<Week> convertWeeksDomain(Set<WeekEntity> weekEntities) {
        return weekEntities.stream()
                .map(this::toWeekDomain)
                .collect(Collectors.toSet());
    }

    private Week toWeekDomain(WeekEntity weekEntity){
        return null;
    }

  }
