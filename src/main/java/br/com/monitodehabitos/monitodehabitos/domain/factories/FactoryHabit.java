package br.com.monitodehabitos.monitodehabitos.domain.factories;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

import java.time.LocalDate;

public class FactoryHabit {
    private Habit habit;

    public Habit withDescriptionAndDate(Long id, Client client, String description, LocalDate start) throws HabitExeption {
        if (id == null) {
            throw new HabitExeption("ID do hábito não pode ser nulo");
        }
        if (client == null) {
            throw new HabitExeption("Cliente não pode ser nulo");
        }
        if (description == null) {
            throw new HabitExeption("Descrição do hábito não pode ser nula");
        }
        if (start == null) {
            throw new HabitExeption("Data de início do hábito não pode ser nula");
        }
        this.habit = new Habit(id, client, description, start);
        return this.habit;
    }

    public Habit update(String description, LocalDate start) throws HabitExeption {
        if (description == null) {
            throw new HabitExeption("Descrição do hábito não pode ser nula");
        }
        if (start == null) {
            throw new HabitExeption("Data de início do hábito não pode ser nula");
        }
        this.habit = new Habit(null, null, description, start);
        return this.habit;
    }
}
