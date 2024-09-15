package br.com.monitodehabitos.monitodehabitos.domain.factories;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

import java.time.LocalDate;

public class FactoryHabit {
    private Habit habit;

    public Habit withDescriptionAndDate(Client client, String description, LocalDate start) throws HabitExeption {
        this.habit = new Habit(client, description, start);
        return this.habit;
    }
}
