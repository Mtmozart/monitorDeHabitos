package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Progress;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

public class WeekEntityMapper {

    public WeekEntity toWeekEntity(Week week) {
        return null;
    }

    public Week toWeekDomain(WeekEntity weekEntity) {
        return new Week(
                weekEntity.getId(),
                weekEntity.getStartDate(),
                weekEntity.getEndDate(),
                weekEntity.getPercentagePerDay(),
                progressDomainList(weekEntity.getProgresses()),
                weekEntity.getTotalPercentage()

        );
    }

    private HabitEntity toHabitEntity(Habit habit) {
        return null;
    }

    private ClientEntity toClientEntity(Client client) {
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

    private AddressEntity toAddressEntity(Address address) {
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


    private Habit toHabitDomain(HabitEntity habitEntity) {
        return null;
    }

    private Client toClientDomain(ClientEntity client) {
        return new Client(
                client.getId(),
                client.getEmail(),
                client.getPassword(),
                client.getName(),
                client.getCreatedAt(),
                client.getUpdatedAt(),
                toAddressDomain(client.getAddressEntity()),
                client.getTypeUser(),
                client.getClient(),
                null
        );

    }

    private Address toAddressDomain(AddressEntity address) {
        return new Address(
                address.getCep(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getNeighborhood(),
                address.getNumber(),
                address.getComplement()
        );
    }


    private List<ProgressEntity> progressEntityList(List<Progress> progresses) {
        return progresses.stream().map(this::toProgressEntity).collect(Collectors.toList());

    }

    private ProgressEntity toProgressEntity(Progress progress) {
        return new ProgressEntity(
                progress.getId(),
                progress.getCurrentDate(),
                progress.getProgressEnumStatus(),
                toWeekEntity(progress.getWeek())
        );
    }

    private List<Progress> progressDomainList(List<ProgressEntity> progressEntities) {
        return progressEntities.stream().map(this::toProgressDomain).collect(Collectors.toList());

    }

    private Progress toProgressDomain(ProgressEntity progressEntity) {
        return new Progress(
                progressEntity.getId(),
                progressEntity.getCurrentDate(),
                progressEntity.getCompleted(),
                null
        );
    }

}
