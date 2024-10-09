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
                habit.getDay(),
                habit.getPercentageForDay(),
                null,
                habit.getCurrentDay()
        );
    }


    public Habit toHabitWithAllParamenters(HabitEntity habitEntity) {
        return new Habit(
                habitEntity.getId(),
                habitEntity.getDescription(),
                habitEntity.getDone(),
                habitEntity.getStart(),
                habitEntity.getEnd(),
                habitEntity.getDay(),
                habitEntity.getPercentageForDay(),
                null,
                habitEntity.getCurrentDay()
        );
    }
}
