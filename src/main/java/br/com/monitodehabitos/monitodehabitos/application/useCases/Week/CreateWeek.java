package br.com.monitodehabitos.monitodehabitos.application.useCases.Week;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;

public class CreateWeek {
    private final WeekRepository weekRepository;

    public CreateWeek(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }
    public Week create(Week week){
        return this.weekRepository.save(week);
    }

}
