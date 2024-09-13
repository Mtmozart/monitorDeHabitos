package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntityRepository;

import java.util.List;

public class HabitRepositoryJPA implements HabitRepository {

    private final HabitEntityRepository habitEntityRepository;

    public HabitRepositoryJPA(HabitEntityRepository habitEntityRepository) {
        this.habitEntityRepository = habitEntityRepository;
    }


    @Override
    public Habit save(Habit habit) {
        return null;
    }

    @Override
    public Habit update(Long id, Habit newHabit) {
        return null;
    }

    @Override
    public Habit findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Habit> findAllByUser(Long userId) {
        return null;
    }
}
