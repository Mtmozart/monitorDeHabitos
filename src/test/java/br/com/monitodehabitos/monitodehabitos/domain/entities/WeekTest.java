package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

class WeekTest {

    @Test
    @DisplayName("Should be add, total percentage")
    void addPercentage() throws HabitExeption, WeekException {
        FactoryHabit factoryHabit = new FactoryHabit();

        FactoryWeek factoryWeek = new FactoryWeek();
        Week week = factoryWeek.createWeekWithStartDateAndEndDate(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 2));
        week.addPercentage(week.getPercentagePerDay());
        Assertions.assertEquals(50.0, week.getPercentagePerDay());
    }

    @Test
    @DisplayName("Should be until 100% ")
    void scenario02() throws HabitExeption, WeekException {
        Client client = Mockito.mock(Client.class);

        FactoryWeek factoryWeek = new FactoryWeek();
        Week week = factoryWeek.createWeekWithStartDateAndEndDate(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 2));
        week.addPercentage(week.getPercentagePerDay());
        week.addPercentage(week.getPercentagePerDay());
        Assertions.assertEquals(100, week.getTotalPercentage());
    }

    @Test
    @DisplayName("Should be until 100% if the add exceed 100 and be smaller than it plus 100 ")
    void scenario03() throws HabitExeption, WeekException {

        FactoryWeek factoryWeek = new FactoryWeek();
        Week week = factoryWeek.createWeekWithStartDateAndEndDate(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 2));
        week.addPercentage(week.getPercentagePerDay());
        week.addPercentage(week.getPercentagePerDay());
        week.addPercentage(week.getPercentagePerDay());

        Assertions.assertEquals(100, week.getTotalPercentage());
    }

    @Test
    @DisplayName("Should be remove, 50% ")
    void scenario4() throws HabitExeption, WeekException {

        FactoryWeek factoryWeek = new FactoryWeek();
        Week week = factoryWeek.createWeekWithStartDateAndEndDate(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 2));
        week.addPercentage(week.getPercentagePerDay());
        week.addPercentage(week.getPercentagePerDay());
        week.subtractPercentage(week.getPercentagePerDay());
        Assertions.assertEquals(50, week.getTotalPercentage());
    }

    @Test
    @DisplayName("Should be return 0 ")
    void scenario5() throws HabitExeption, WeekException {

        FactoryHabit factoryHabit = new FactoryHabit();
        FactoryWeek factoryWeek = new FactoryWeek();
        Week week = factoryWeek.createWeekWithStartDateAndEndDate(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 2));
        week.addPercentage(week.getPercentagePerDay());
        week.subtractPercentage(week.getPercentagePerDay());
        week.subtractPercentage(week.getPercentagePerDay());
        Assertions.assertEquals(0, week.getTotalPercentage());
    }

}