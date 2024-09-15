package br.com.monitodehabitos.monitodehabitos.application.useCases.Habit;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;

public class FindHabit {
    private final HabitRepository habitRepository;

    public FindHabit(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }
    public Habit findById(Long id){
        return this.habitRepository.findById(id);
    }
}
