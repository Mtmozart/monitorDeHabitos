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
        return null;
    }

    @Override
    public void delete(Long id) throws WeekException {

    }

    @Override
    public Boolean addPercentage(double add) {
        return null;
    }

    @Override
    public Boolean removePercentage(double add) {
        return null;
    }

    @Override
    public double getPercentage() {
        return 0;
    }
}
