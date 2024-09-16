package br.com.monitodehabitos.monitodehabitos.infra.gateways;

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

}
