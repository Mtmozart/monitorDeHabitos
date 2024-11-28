package br.com.monitodehabitos.monitodehabitos.application.useCases.Week;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.exception.ProgressException;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.time.LocalDate;

public class ChangeProgress {

    private final WeekRepository weekRepository;

    public ChangeProgress(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }

    public void changeProgress(String weekid, LocalDate progressDate) throws ProgressException, WeekException {
        this.weekRepository.changeProgress(weekid, progressDate);
    }
}
