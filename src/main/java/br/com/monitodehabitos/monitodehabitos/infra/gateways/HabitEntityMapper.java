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
        return new HabitEntity(
                habit.getId(),
                habit.getDescription(),
                habit.getDone(),
                habit.getStart(),
                habit.getEnd(),
                habit.getPercentageForDay(),
                toClientEntity(habit.getClient()),
                habit.getCurrentDay()
        );
    }

    public Habit toHabitDomainWithAllParameters(HabitEntity habitEntity) {
        return new Habit(
                habitEntity.getId(),
                habitEntity.getDescription(),
                habitEntity.getDone(),
                habitEntity.getStart(),
                habitEntity.getEnd(),
                habitEntity.getPercentageForDay(),
                toClientDomain(habitEntity.getClientEntity()),
                habitEntity.getCurrentDay(),
                toProgressDomainMapper(habitEntity.getProgressEntities())
        );
    }

    public Client toClientDomain(ClientEntity clientEntity) {
        if (clientEntity == null) return null;

        return new Client(
                clientEntity.getId(),
                clientEntity.getEmail(),
                clientEntity.getPassword(),
                clientEntity.getName(),
                clientEntity.getCreatedAt(),
                clientEntity.getUpdatedAt(),
                toAddressDomain(clientEntity.getAddressEntity()),
                clientEntity.getTypeUser(),
                clientEntity.getClient(),
                null,
                null
        );
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
        return new ProgressEntity(
                progress.getDate(),
                progress.getCompleted()
        );
    }

    public List<ProgressEntity> toProgressEntityMapper(Habit habit) {
        return habit.getProgress().stream()
                .map(this::toProgressEntity)
                .collect(Collectors.toList());
    }

    public Progress toProgressDomain(ProgressEntity progressEntity) {
        return new Progress(
                progressEntity.getCurrentDate(),
                progressEntity.getCompleted()
        );
    }

    public List<Progress> toProgressDomainMapper(List<ProgressEntity> progressEntities) {
        return progressEntities.stream()
                .map(this::toProgressDomain)
                .collect(Collectors.toList());
    }
}
