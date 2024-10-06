package br.com.monitodehabitos.monitodehabitos.application.useCases.Week;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;


public class RemovePercentage {
    private final WeekRepository weekRepository;

    public RemovePercentage(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }

    public Boolean removePercentage(double percentage) {
        return this.weekRepository.removePercentage(percentage);
    }
}
