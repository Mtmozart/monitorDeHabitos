package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.enums.HabitsErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntityRespository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Habit findById(Long id) throws HabitExeption {
        Optional<HabitEntity> habitEntity = this.habitEntityRespository.findById(id);
        if(habitEntity.isEmpty()){
            throw new HabitExeption(HabitsErrorEnum.HBT0003.getMessage());
        }
        return this.habitEntityMapper.toHabitWithAllParamenters(habitEntity.get());
    }

    @Override
    public void delete(Long id) throws HabitExeption {
        Boolean exists = this.habitEntityRespository.existsById(id);
        if(!exists){
            throw new HabitExeption(HabitsErrorEnum.HBT0003.getMessage());
        }
        this.habitEntityRespository.deleteById(id);
    }

    @Override
    public List<Habit> findAllByUser(Long userId) {

        List<HabitEntity> habitEntities = this.habitEntityRespository.findAllByClientId(userId);
        if (habitEntities.isEmpty()) {
            return List.of();
        }
        return habitEntities.stream()
                .map(this.habitEntityMapper::toHabitWithAllParamenters)
                .toList();


    }
}
