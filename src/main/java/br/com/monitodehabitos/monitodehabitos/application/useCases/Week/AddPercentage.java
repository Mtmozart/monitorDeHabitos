package br.com.monitodehabitos.monitodehabitos.application.useCases.Week;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;

public class AddPercentage {
    private final WeekRepository weekRepository;

    public AddPercentage(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }

    public Boolean addPercentage(double percentage) {
        return this.weekRepository.addPercentage(percentage);
    }
}