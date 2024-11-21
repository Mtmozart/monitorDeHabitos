package br.com.monitodehabitos.monitodehabitos.application.useCases.Week;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.util.UUID;

public class FindWeekById {
    private final WeekRepository weekRepository;

    public FindWeekById(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }
    public Week findById(String id) throws WeekException {
        return this.weekRepository.findById(id);
    }
    
}
