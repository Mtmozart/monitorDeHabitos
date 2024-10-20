package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.enums.WeekErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.WeekEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.WeekEntityRepository;

import java.util.Optional;

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
        System.out.println(week);
        WeekEntity weekEntitySave = this.weekEntityRepository.save(weekEntity);
        return this.weekEntityMapper.toWeekDomain(weekEntitySave);
    }

    @Override
    public Week findById(Long id) throws WeekException {
        Optional<WeekEntity> weekEntity = this.weekEntityRepository.findById(id);
        if (weekEntity.isPresent()) {
            return this.weekEntityMapper.toWeekDomain(weekEntity.get());
        }
        throw new WeekException(WeekErrorEnum.HBT0014.getMessage());
    }


        @Override
        public void delete (Long id) throws WeekException {

            if (!this.weekEntityRepository.existsById(id)) {
                throw new WeekException(WeekErrorEnum.HBT0014.getMessage());
            }
        this.weekEntityRepository.deleteById(id);
        }

        @Override
        public Boolean addPercentage ( double add, Long id) throws WeekException {
            Week week = this.findById(id);
            week.addPercentage(add);
            WeekEntity weekEntity = this.weekEntityMapper.toWeekEntity(week);
            this.weekEntityRepository.save(weekEntity);
            return true;
        }

        @Override
        public Boolean removePercentage ( double remove, Long id) throws WeekException {
            Week week = this.findById(id);
            week.subtractPercentage(remove);
            WeekEntity weekEntity = this.weekEntityMapper.toWeekEntity(week);
            this.weekEntityRepository.save(weekEntity);
            return true;
        }

        @Override
        public double getPercentage () {
            return 0;
        }
    }
