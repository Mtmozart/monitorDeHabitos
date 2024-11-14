package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.enums.HabitsErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntityRespository;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ProgressEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ProgressEntityRepository;
import br.com.monitodehabitos.monitodehabitos.infra.utils.HabitChangeEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class HabitRepositoryJPA implements HabitRepository {
    private final HabitEntityRespository habitEntityRespository;
    private final HabitEntityMapper habitEntityMapper;
    private final ProgressEntityRepository progressEntityRepository;
    private final ApplicationEventPublisher eventPublisher;

    public HabitRepositoryJPA(HabitEntityRespository habitEntityRespository, HabitEntityMapper habitEntityMapper, ProgressEntityRepository progressEntityRepository, ApplicationEventPublisher eventPublisher) {
        this.habitEntityRespository = habitEntityRespository;
        this.habitEntityMapper = habitEntityMapper;
        this.progressEntityRepository = progressEntityRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Habit save(Habit habit) {
        HabitEntity habitEntity = this.habitEntityMapper.toHabitEntityCreate(habit);
        HabitEntity savedHabitEntity = this.habitEntityRespository.save(habitEntity);
        List<ProgressEntity> progressEntities = this.habitEntityMapper.toProgressEntityMapper(habit);
        System.out.println(habit.getProgress());
        for (ProgressEntity p : progressEntities) {
            p.addHabitEntity(savedHabitEntity);
            savedHabitEntity.addProgress(p);
            this.progressEntityRepository.save(p);
        }

        return this.habitEntityMapper.toHabitDomainWithAllParameters(savedHabitEntity);
    }

    @Override
    public Habit update(Long id, Habit newHabit) throws HabitExeption {
        Habit habit = this.findById(id);
        habit.update(newHabit);
        HabitEntity savedHabitEntity = this.habitEntityRespository.save(this.habitEntityMapper.toHabitEntityCreate(habit));

        List<ProgressEntity> progressEntities = this.habitEntityMapper.toProgressEntityMapper(habit);
        for (ProgressEntity p : progressEntities) {
            p.addHabitEntity(savedHabitEntity);
            savedHabitEntity.addProgress(p);
            this.progressEntityRepository.save(p);
        }
        return this.habitEntityMapper.toHabitDomainWithAllParameters(savedHabitEntity);
    }

    @Override
    public Habit findById(Long id) throws HabitExeption {
        Optional<HabitEntity> habitEntity = this.habitEntityRespository.findHabit(id);
        if (habitEntity.isEmpty()) {
            throw new HabitExeption(HabitsErrorEnum.HBT0003.getMessage());
        }
        return this.habitEntityMapper.toHabitDomainWithAllParameters(habitEntity.get());
    }

    @Override
    public void delete(Long id) throws HabitExeption {
        boolean exists = this.habitEntityRespository.existsById(id);
        if (!exists) {
            throw new HabitExeption(HabitsErrorEnum.HBT0003.getMessage());
        }
        this.habitEntityRespository.deleteById(id);
    }

    @Override
    public Habit changeDone(Long id, LocalDate dateHabit) throws HabitExeption, WeekException {
        Habit habit = this.findById(id);
        var change = habit.changeDo(dateHabit);
        HabitEntity habitEntity = this.habitEntityRespository.save(this.habitEntityMapper.toHabitEntityWithAllParamentrs(habit));
        eventPublisher.publishEvent(new HabitChangeEvent(this, habitEntity, change));

        return habit;
    }

    @Override
    public List<Habit> findAllByUser(Long userId) {
        List<HabitEntity> habitEntities = this.habitEntityRespository.findAllByClientId(userId);
        if (habitEntities.isEmpty()) {
            return List.of();
        }
        return habitEntities.stream()
                .map(this.habitEntityMapper::toHabitDomainWithAllParameters)
                .toList();
    }

}
