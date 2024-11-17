package br.com.monitodehabitos.monitodehabitos.infra.utils;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.AddPercentage;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.RemovePercentage;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("Observer")
public class HabitChangeListener {


    public HabitChangeListener() {
    }

    @EventListener
    public void handleHabitChangedEvent(HabitChangeEvent event) throws WeekException {
        HabitEntity habitEntity = event.getHabitEntity();
        boolean change = event.isChange();

        }

}
