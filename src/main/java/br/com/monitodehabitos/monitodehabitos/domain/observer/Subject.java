package br.com.monitodehabitos.monitodehabitos.domain.observer;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(Habit habit, boolean change) throws WeekException;

}
