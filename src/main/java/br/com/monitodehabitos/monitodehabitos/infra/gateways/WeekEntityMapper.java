package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.AddressEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.WeekEntity;

public class WeekEntityMapper {

    public WeekEntity toWeekEntity(Week week) {
        return new WeekEntity(
                week.getId(),
                null,
                this.toClientEntity(week.getClient()),
                1,
                week.getTotalePercentage()
        );
    }

    public Week toWeekDomain(WeekEntity weekEntity) {
        return new Week(
                weekEntity.getId(),
                null,
                this.toClientDomain(weekEntity.getClientEntity())
        );
    }

    private HabitEntity toHabitEntity(Habit habit) {
        return new HabitEntity(
                habit.getId(),
                habit.getDescription(),
                habit.getDone(),
                habit.getStart(),
                habit.getEnd(),
                habit.getPercentageForDay(),
                null,
                habit.getCurrentDay()
        );
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
        return new Habit(
                habitEntity.getId(),
                habitEntity.getDescription(),
                habitEntity.getDone(),
                habitEntity.getStart(),
                habitEntity.getEnd(),
                habitEntity.getPercentageForDay(),
                toClientDomain(habitEntity.getClientEntity()),
                habitEntity.getCurrentDay(),
                null
        );
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
                client.getClient()
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

}
