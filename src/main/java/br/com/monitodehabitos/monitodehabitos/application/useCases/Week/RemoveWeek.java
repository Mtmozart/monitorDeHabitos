package br.com.monitodehabitos.monitodehabitos.application.useCases.Week;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

public class RemoveWeek {
    private final WeekRepository weekRepository;

    public RemoveWeek(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }
    public void create(Long id) throws WeekException {
        this.weekRepository.delete(id);
    }
}
