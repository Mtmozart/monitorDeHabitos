package br.com.monitodehabitos.monitodehabitos.application.useCases.Week;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;

public class ReturnPercentage {

    private final WeekRepository weekRepository;

    public ReturnPercentage(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }

    public double returnPercentage() {
        return this.weekRepository.getPercentage();
    }
}
