package br.com.monitodehabitos.monitodehabitos.application.useCases.Week;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

public class AddPercentage {
    private final WeekRepository weekRepository;

    public AddPercentage(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }

    public Boolean addPercentage(double percentage, Long id) throws WeekException {
        return this.weekRepository.addPercentage(percentage, id);
    }
}