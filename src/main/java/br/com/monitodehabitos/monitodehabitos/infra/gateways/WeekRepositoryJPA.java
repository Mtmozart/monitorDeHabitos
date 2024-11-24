package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.enums.WeekErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.WeekEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.WeekEntityRepository;

import java.util.Optional;
import java.util.UUID;

public class WeekRepositoryJPA implements WeekRepository {


    private WeekEntityRepository weekEntityRepository;
    private WeekEntityMapper weekEntityMapper;

    public WeekRepositoryJPA(){}

    public WeekRepositoryJPA(WeekEntityRepository weekEntityRepository, WeekEntityMapper weekEntityMapper) {
        this.weekEntityRepository = weekEntityRepository;
        this.weekEntityMapper = weekEntityMapper;
    }

    @Override
    public Week save(Week week) {
        WeekEntity weekEntity = this.weekEntityMapper.toWeekEntity(week);
        WeekEntity weekEntitySave = this.weekEntityRepository.save(weekEntity);
        return this.weekEntityMapper.toWeekDomain(weekEntitySave);
    }

    @Override
    public Week findById(String id) throws WeekException {
        Optional<WeekEntity> weekEntity = this.weekEntityRepository.findById(id);
        if (weekEntity.isPresent()) {
            return this.weekEntityMapper.toWeekDomain(weekEntity.get());
        }
        throw new WeekException(WeekErrorEnum.HBT0014.getMessage());
    }


    @Override
    public void delete(String id) throws WeekException {

        if (!this.weekEntityRepository.existsById(id)) {
            throw new WeekException(WeekErrorEnum.HBT0014.getMessage());
        }
        this.weekEntityRepository.deleteById(id);
    }

    @Override
    public Boolean addPercentage(double add, String habitId) throws WeekException {
//        Optional<WeekEntity> weekEntity = this.weekEntityRepository.findWeekByHabitId(habitId);
//        if(weekEntity.isEmpty()){
//            System.out.println("Semana inexistente");
//        }
//        Week week = this.weekEntityMapper.toWeekDomain(weekEntity.get());
//        week.addPercentage(add);
//        WeekEntity weekEntitySave = this.weekEntityMapper.toWeekEntity(week);
//        this.weekEntityRepository.save(weekEntitySave);
//        return true;
        return null;
    }

    @Override
    public Boolean removePercentage(double remove, String habitId) throws WeekException {
//        Optional<WeekEntity> weekEntity = this.weekEntityRepository.findWeekByHabitId(habitId);
//        if(weekEntity.isEmpty()){
//            System.out.println("Semana inexistente");
//        }
//        Week week = this.weekEntityMapper.toWeekDomain(weekEntity.get());
//        week.subtractPercentage(remove);
//        WeekEntity weekEntitySave = this.weekEntityMapper.toWeekEntity(week);
//        this.weekEntityRepository.save(weekEntitySave);
//        return true;
        return null;
    }

    @Override
    public double getPercentage() {
        return 0;
    }

}
