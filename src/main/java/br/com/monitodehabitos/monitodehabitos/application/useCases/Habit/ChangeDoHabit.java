package br.com.monitodehabitos.monitodehabitos.application.useCases.Habit;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

public class ChangeDoHabit {
    private final HabitRepository habitRepository;

    public ChangeDoHabit(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public Boolean changeDoHabit(Long id) throws HabitExeption {
        return habitRepository.changeDone(id);
    }
}
