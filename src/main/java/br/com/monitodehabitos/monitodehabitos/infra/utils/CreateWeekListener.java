package br.com.monitodehabitos.monitodehabitos.infra.utils;

import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("ObserverCreateWeek")
public class CreateWeekListener {

    private final HabitEntity habitEntity;
    private final ClientEntity clientEntity;


    public CreateWeekListener(HabitEntity habitEntity, ClientEntity clientEntity) {
        this.habitEntity = habitEntity;
        this.clientEntity = clientEntity;
    }

    @EventListener
    public void createWeek(){}
}
