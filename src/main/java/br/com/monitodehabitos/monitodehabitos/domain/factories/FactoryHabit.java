package br.com.monitodehabitos.monitodehabitos.domain.factories;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.HabitStatus;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

import java.time.LocalDate;
import java.util.UUID;

public class FactoryHabit {
    private Habit habit;

    public Habit withDescriptionAndDate(Client client, String description, HabitStatus done, LocalDate start, LocalDate end) throws HabitExeption {

        if (client == null) {
            throw new HabitExeption("Cliente não pode ser nulo");
        }
        if (description == null) {
            throw new HabitExeption("Descrição do hábito não pode ser nula");
        }
        if (start == null) {
            throw new HabitExeption("Data de início do hábito não pode ser nula");
        }
        return this.habit = new Habit(description, done,  start, end, client);
    }

    public Habit update(String description, LocalDate start, LocalDate end) throws HabitExeption {
        return this.habit = new Habit(null, null, description, start, end);
    }

    public Habit updateNoDateStater(String description, HabitStatus done) throws HabitExeption {
        return this.habit = new Habit(null, null, description, done);

    }
}
