package br.com.monitodehabitos.monitodehabitos.infra.utils;

import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import org.springframework.context.ApplicationEvent;

public class HabitCreateWeekEvent extends ApplicationEvent {

    private final HabitEntity habitEntity;
    private final ClientEntity clientEntity;

    public HabitCreateWeekEvent(Object source, HabitEntity habitEntity, ClientEntity clientEntity) {
        super(source);
        this.habitEntity = habitEntity;
        this.clientEntity = clientEntity;
    }

    public HabitEntity getHabitEntity() {
        return habitEntity;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }
}
