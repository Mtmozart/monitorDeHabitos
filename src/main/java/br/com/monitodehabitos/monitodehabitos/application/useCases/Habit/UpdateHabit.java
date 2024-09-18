package br.com.monitodehabitos.monitodehabitos.application.useCases.Habit;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

public class UpdateHabit {
    private final HabitRepository habitRepository;

    public UpdateHabit(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public Habit findById(Long id) throws HabitExeption {
        return this.habitRepository.findById(id);
    }
}
