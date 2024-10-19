package br.com.monitodehabitos.monitodehabitos.domain.factories;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;

import java.util.List;

public class FactoryWeek {
    private Week week;

    public Week createWeekWithIdHabitClient(Long id, Habit habit, Client client){
        this.week = new Week(id, habit, client);
        return this.week;
    }
}
