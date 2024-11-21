package br.com.monitodehabitos.monitodehabitos.application.useCases.Week;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.util.UUID;


public class RemovePercentage {
    private final WeekRepository weekRepository;

    public RemovePercentage(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }

    public Boolean removePercentage(double percentage, String id) throws WeekException {
        return this.weekRepository.removePercentage(percentage, id);
    }
}
