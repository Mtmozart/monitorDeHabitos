package br.com.monitodehabitos.monitodehabitos.application.useCases.Habit;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;

import java.util.List;
import java.util.UUID;

public class FindAllByUser {
    private final HabitRepository habitRepository;

    public FindAllByUser(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public List<Habit> findAllByUser(String userId){
        return this.habitRepository.findAllByUser(userId);
    }
}
