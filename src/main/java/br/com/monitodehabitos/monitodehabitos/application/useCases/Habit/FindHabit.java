package br.com.monitodehabitos.monitodehabitos.application.useCases.Habit;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

import java.util.UUID;

public class FindHabit {
    private final HabitRepository habitRepository;

    public FindHabit(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }
    public Habit findById(String id) throws HabitExeption {
        return this.habitRepository.findById(id);
    }
}
