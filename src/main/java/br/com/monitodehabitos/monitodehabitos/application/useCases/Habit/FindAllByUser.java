package br.com.monitodehabitos.monitodehabitos.application.useCases.Habit;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;

import java.util.List;

public class FindAllByUser {

    private final HabitRepository habitRepository;

    public FindAllByUser(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public List<Habit> findAllByUser(Long userId){
        return this.habitRepository.findAllByUser(userId);
    }
}
