package br.com.monitodehabitos.monitodehabitos.application.useCases.Week;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.util.UUID;

public class AddPercentage {
    private final WeekRepository weekRepository;

    public AddPercentage(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }

    public Boolean addPercentage(double percentage, String id) throws WeekException {
        return this.weekRepository.addPercentage(percentage, id);
    }
}