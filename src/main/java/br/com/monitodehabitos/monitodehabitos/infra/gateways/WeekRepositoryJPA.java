package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.WeekEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.WeekEntityRepository;

public class WeekRepositoryJPA implements WeekRepository {

    private final WeekEntityRepository weekEntityRepository;
    private final WeekEntityMapper weekEntityMapper;

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
    public Week findById(Long id) throws WeekException {
        WeekEntity weekEntity = this.weekEntityRepository.findById(id).get();
        Week week = this.weekEntityMapper.toWeekDomain(weekEntity);
        return week;
    }

    @Override
    public void delete(Long id) throws WeekException {

    }

    @Override
    public Boolean addPercentage(double add, Long id) throws WeekException {
        Week week = this.findById(id);
        week.addPercentage(add);
        WeekEntity weekEntity = this.weekEntityMapper.toWeekEntity(week);
        this.weekEntityRepository.save(weekEntity);
        return true;
    }

    @Override
    public Boolean removePercentage(double remove,  Long id) throws WeekException {
        Week week = this.findById(id);
        System.out.println(week);
        System.out.println(remove);
        week.subtractPercentage(remove);
        WeekEntity weekEntity = this.weekEntityMapper.toWeekEntity(week);
        this.weekEntityRepository.save(weekEntity);
        return true;
    }

    @Override
    public double getPercentage() {
        return 0;
    }
}
