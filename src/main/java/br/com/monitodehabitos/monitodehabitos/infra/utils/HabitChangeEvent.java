package br.com.monitodehabitos.monitodehabitos.infra.utils;

import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import org.springframework.context.ApplicationEvent;


public class HabitChangeEvent extends ApplicationEvent {
    private final HabitEntity habitEntity;
    private final boolean change;
    public HabitChangeEvent(Object source, HabitEntity habitEntity, boolean change) {
        super(source);
        this.habitEntity = habitEntity;
        this.change = change;
    }

    public HabitEntity getHabitEntity() {
        return habitEntity;
    }

    public boolean isChange() {
        return change;
    }
}
