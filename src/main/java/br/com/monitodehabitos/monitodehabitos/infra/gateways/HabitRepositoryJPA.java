package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntityRespository;

import java.util.List;

public class HabitRepositoryJPA implements HabitRepository {
    private final HabitEntityRespository habitEntityRespository;
    private final HabitEntityMapper habitEntityMapper;

    public HabitRepositoryJPA(HabitEntityRespository habitEntityRespository, HabitEntityMapper habitEntityMapper) {
        this.habitEntityRespository = habitEntityRespository;
        this.habitEntityMapper = habitEntityMapper;
    }

    @Override
    public Habit save(Habit habit) {
        HabitEntity habitEntity = this.habitEntityMapper.toHabitEntityCreate(habit);
        this.habitEntityRespository.save(habitEntity);
        return habit;
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
