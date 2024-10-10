package br.com.monitodehabitos.monitodehabitos.domain.observer;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

public interface Observer {
    void update(Habit habit, boolean change) throws WeekException;


}
