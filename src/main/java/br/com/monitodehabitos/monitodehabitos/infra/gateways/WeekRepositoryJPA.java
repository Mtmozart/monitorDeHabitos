package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Progress;
import br.com.monitodehabitos.monitodehabitos.domain.entities.ProgressEnumStatus;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.enums.WeekErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.ProgressException;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ProgressEntityRepository;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.WeekEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.WeekEntityRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

public class WeekRepositoryJPA implements WeekRepository {

    private WeekEntityRepository weekEntityRepository;
    private WeekEntityMapper weekEntityMapper;
    private ProgressEntityRepository progressEntityRepository;

    public WeekRepositoryJPA() {
    }

    public WeekRepositoryJPA(WeekEntityRepository weekEntityRepository, WeekEntityMapper weekEntityMapper, ProgressEntityRepository progressEntityRepository) {
        this.weekEntityRepository = weekEntityRepository;
        this.weekEntityMapper = weekEntityMapper;
        this.progressEntityRepository = progressEntityRepository;
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
        throw new WeekException(WeekErrorEnum.WK0004.getMessage());
    }


    @Override
    public void delete(String id) throws WeekException {
        if (!this.weekEntityRepository.existsById(id)) {
            throw new WeekException(WeekErrorEnum.WK0003.getMessage());
        }
        this.weekEntityRepository.deleteById(id);
    }

    @Override
    public Boolean addPercentage(double add, String id) throws WeekException {
        Optional<WeekEntity> weekEntity = this.weekEntityRepository.findById(id);
        if (weekEntity.isEmpty()) {
            throw new WeekException(WeekErrorEnum.WK0003.getMessage());
        }
        Week week = this.weekEntityMapper.toWeekDomain(weekEntity.get());
        week.addPercentage(add);
        this.weekEntityRepository.updateProgressWeek(weekEntity.get().getId(), week.getTotalPercentage());
        return true;
    }

    @Override
    @Transactional
    public Boolean removePercentage(double remove, String weekId) throws WeekException {
        Optional<WeekEntity> weekEntity = this.weekEntityRepository.findById(weekId);
        if (weekEntity.isEmpty()) {
            System.out.println("Semana inexistente");
        }
        Week week = this.weekEntityMapper.toWeekDomain(weekEntity.get());
        week.subtractPercentage(remove);
        this.weekEntityRepository.updateProgressWeek(week.getId(), week.getTotalPercentage());
        return true;

    }

    @Transactional
    @Override
    public void changeProgress(String weekid, LocalDate date) throws ProgressException, WeekException {
        Optional<WeekEntity> weekEntity = this.weekEntityRepository.findById(weekid);
        if (weekEntity.isEmpty()) {
            throw new WeekException(WeekErrorEnum.WK0004.getMessage());
        }
        Week week = weekEntityMapper.toWeekDomain(weekEntity.get());
        Progress progress = week.changeProgressStatus(date);
        if (progress.getProgressEnumStatus().equals(ProgressEnumStatus.NOT_STARTED)) {
            this.removePercentage(weekEntity.get().getPercentagePerDay(), weekEntity.get().getId());
        } else {
            this.addPercentage(weekEntity.get().getPercentagePerDay(), weekEntity.get().getId());
        }
        this.progressEntityRepository.updateProgressStatus(progress.getId(), progress.getProgressEnumStatus());
    }

    @Override
    public double getPercentage() {
        return 0;
    }

}
