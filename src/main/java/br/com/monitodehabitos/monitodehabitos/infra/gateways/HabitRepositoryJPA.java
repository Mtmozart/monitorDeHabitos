package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.HabitStatus;
import br.com.monitodehabitos.monitodehabitos.domain.enums.HabitsErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntityRespository;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ProgressEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class HabitRepositoryJPA implements HabitRepository {
    private final HabitEntityRespository habitEntityRespository;
    private final HabitEntityMapper habitEntityMapper;

    public HabitRepositoryJPA(HabitEntityRespository habitEntityRespository, HabitEntityMapper habitEntityMapper) {
        this.habitEntityRespository = habitEntityRespository;
        this.habitEntityMapper = habitEntityMapper;

    }

    @Override
    @Transactional
    public Habit save(Habit habit) {
        HabitEntity habitEntity = this.habitEntityMapper.toHabitEntityCreate(habit);
        HabitEntity savedHabitEntity = this.habitEntityRespository.save(habitEntity);
        return this.habitEntityMapper.toHabitDomainWithAllParameters(savedHabitEntity);

    }

    @Override
    @Transactional
    public Habit update(String id, Habit newHabit) throws HabitExeption {
        Habit habit = this.findById(id);
        habit.update(newHabit);
       this.habitEntityRespository.updateHabitStatusAndDone(habit.getId(), habit.getDescription(), habit.getDone());
        return habit;
    }

    @Override
    public Habit findById(String id) throws HabitExeption {
        Optional<HabitEntity> habitEntity = this.habitEntityRespository.findHabit(id);
        if (habitEntity.isEmpty()) {
            throw new HabitExeption(HabitsErrorEnum.HBT0003.getMessage());
        }
        return this.habitEntityMapper.toHabitDomainWithAllParameters(habitEntity.get());
    }

    @Override
    @Transactional
    public void delete(String id) throws HabitExeption {
        boolean exists = this.habitEntityRespository.existsById(id);
        if (!exists) {
            throw new HabitExeption(HabitsErrorEnum.HBT0003.getMessage());
        }
        this.habitEntityRespository.deleteById(id);
    }

    @Override
    @Transactional
    public Habit changeDone(String id, HabitStatus status) throws HabitExeption {
        Habit habit = this.findById(id);
        habit.changeStatus(status);
        this.habitEntityRespository.updateHabitStatus(habit.getId(), habit.getDone());
        return habit;
    }

    @Override
    public List<Habit> findAllByUser(String userId) {
        List<HabitEntity> habitEntities = this.habitEntityRespository.findAllByClientId(userId);
        if (habitEntities.isEmpty()) {
            return List.of();
        }
        return habitEntities.stream()
                .map(this.habitEntityMapper::toHabitDomainWithAllParameters)
                .toList();
    }

}
