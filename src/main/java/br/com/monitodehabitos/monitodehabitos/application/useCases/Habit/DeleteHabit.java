package br.com.monitodehabitos.monitodehabitos.application.useCases.Habit;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

import java.util.UUID;

public class DeleteHabit {
    private final HabitRepository habitRepository;
    public DeleteHabit(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }
    public void delete(String id) throws HabitExeption {
        this.habitRepository.delete(id);
    }
}
