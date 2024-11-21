package br.com.monitodehabitos.monitodehabitos.domain.factories;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;

import java.time.LocalDate;
import java.util.List;

public class FactoryWeek {
    private Week week;

    public Week createWeekWithStartDateAndEndDate(LocalDate dateStart, LocalDate dateEnd, Habit habit){
        return this.week = new Week(dateStart, dateEnd, habit);
    }
}
