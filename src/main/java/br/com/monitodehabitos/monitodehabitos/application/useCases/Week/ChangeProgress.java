package br.com.monitodehabitos.monitodehabitos.application.useCases.Week;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;

import java.time.LocalDate;

public class ChangeProgress {

    private final WeekRepository weekRepository;

    public ChangeProgress(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }

    public void changeProgress(Week week, LocalDate progressDate) {
        this.weekRepository.changeProgress(week, progressDate);
    }
}
