package br.com.monitodehabitos.monitodehabitos.application.useCases.Habit;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;

public class DeleteHabit {
    private final HabitRepository habitRepository;
    public DeleteHabit(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }
    public void delete(Long id) {
        this.habitRepository.delete(id);
    }
}
