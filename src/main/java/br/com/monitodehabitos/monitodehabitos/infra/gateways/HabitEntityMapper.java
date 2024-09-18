package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.AddressEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;

public class HabitEntityMapper {
    public HabitEntity toHabitEntityCreate(Habit habit) {
        return new HabitEntity(
                habit.getId(),
                habit.getDescription(),
                habit.getDone(),
                habit.getStart(),
                habit.getEnd(),
                habit.getPercentageForDay(),
                new ClientEntity(
                        habit.getClient().getId(),
                        habit.getClient().getEmail(),
                        habit.getClient().getPassword(),
                        habit.getClient().getName(),
                        habit.getClient().getTypeUserEnum(),
                        habit.getClient().getClient(),
                        habit.getClient().getCreatedAt(),
                        habit.getClient().getUpdatedAt(),
                        new AddressEntity(
                                habit.getClient().getAddress().getCep(),
                                habit.getClient().getAddress().getStreet(),
                                habit.getClient().getAddress().getCity(),
                                habit.getClient().getAddress().getState(),
                                habit.getClient().getAddress().getNeighborhood(),
                                habit.getClient().getAddress().getNumber(),
                                habit.getClient().getAddress().getComplement()
                        )
                )
        );
    }


    public Habit toHabitWithAllParamenters(HabitEntity habitEntity) {
        return new Habit(
                habitEntity.getId(),
                habitEntity.getDescription(),
                habitEntity.getDone(),
                habitEntity.getStart(),
                habitEntity.getEnd(),
                habitEntity.getPercentageForDay(),
                new Client(
                        habitEntity.getClientEntity().getId(),
                        habitEntity.getClientEntity().getEmail(),
                        habitEntity.getClientEntity().getPassword(),
                        habitEntity.getClientEntity().getName(),
                        habitEntity.getClientEntity().getCreatedAt(),
                        habitEntity.getClientEntity().getUpdatedAt(),
                        new Address(
                                habitEntity.getClientEntity().getAddressEntity().getCep(),
                                habitEntity.getClientEntity().getAddressEntity().getStreet(),
                                habitEntity.getClientEntity().getAddressEntity().getCity(),
                                habitEntity.getClientEntity().getAddressEntity().getState(),
                                habitEntity.getClientEntity().getAddressEntity().getNeighborhood(),
                                habitEntity.getClientEntity().getAddressEntity().getNumber(),
                                habitEntity.getClientEntity().getAddressEntity().getComplement()
                        ),
                        habitEntity.getClientEntity().getTypeUser(),
                        habitEntity.getClientEntity().getClient()
                )
        );

    }

}
