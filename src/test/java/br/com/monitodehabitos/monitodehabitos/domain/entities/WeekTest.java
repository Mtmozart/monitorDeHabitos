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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeekTest {

    @Test
    @DisplayName("Should be add, 20% ")
    void addPercentage() throws HabitExeption, WeekException {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        //20%
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.of(2024, 9, 24));
        FactoryWeek factoryWeek = new FactoryWeek();
        List<Habit> habitList = new ArrayList<>();
        habitList.add(habit);
        Week week = factoryWeek.createWeekWithIdHabitClient(1L, habitList, client);
        week.addPercentage(habitList.getFirst().getPercentageForDay());
        Assertions.assertEquals(20.0, week.getTotalePercentage());
    }

    @Test
    @DisplayName("Should be until 100% ")
    void scenario02() throws HabitExeption, WeekException {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        //20%
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.of(2024, 9, 24));
        FactoryWeek factoryWeek = new FactoryWeek();
        List<Habit> habitList = new ArrayList<>();
        habitList.add(habit);
        Week week = factoryWeek.createWeekWithIdHabitClient(1L, habitList, client);
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        Assertions.assertEquals(100, week.getTotalePercentage());
    }

    @Test
    @DisplayName("Should be until 100% if the add exceed 100 and be smaller than it plus 100 ")
    void scenario03() throws HabitExeption, WeekException {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        //33.33%
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genérica de algo", LocalDate.of(2024, 9, 26));
        FactoryWeek factoryWeek = new FactoryWeek();
        List<Habit> habitList = new ArrayList<>();
        habitList.add(habit);
        Week week = factoryWeek.createWeekWithIdHabitClient(1L, habitList, client);
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        Assertions.assertEquals(100, week.getTotalePercentage());
    }

    @Test
    @DisplayName("Should be remove, 20% ")
    void scenario4() throws HabitExeption, WeekException {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        //20%
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.of(2024, 9, 24));
        FactoryWeek factoryWeek = new FactoryWeek();
        List<Habit> habitList = new ArrayList<>();
        habitList.add(habit);
        Week week = factoryWeek.createWeekWithIdHabitClient(1L, habitList, client);
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.addPercentage(week.getHabit().getFirst().getPercentageForDay());
        week.subtractPercentage(week.getHabit().getFirst().getPercentageForDay());
        Assertions.assertEquals(80, week.getTotalePercentage());
    }

    @Test
    @DisplayName("Should be return 0 ")
    void scenario5() throws HabitExeption, WeekException {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        //20%
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.of(2024, 9, 24));
        FactoryWeek factoryWeek = new FactoryWeek();
        List<Habit> habitList = new ArrayList<>();
        habitList.add(habit);
        Week week = factoryWeek.createWeekWithIdHabitClient(1L, habitList, client);
        week.subtractPercentage(week.getHabit().getFirst().getPercentageForDay());
        Assertions.assertEquals(0, week.getTotalePercentage());
    }

    @Test
    @DisplayName("Should be return 0 ")
    public void scenario6() throws HabitExeption {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        //20%
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.of(2024, 9, 24));

        FactoryWeek factoryWeek = new FactoryWeek();
        List<Habit> habitList = new ArrayList<>();
        habitList.add(habit);
        Week week = factoryWeek.createWeekWithIdHabitClient(1L, habitList, client);
        week.addHabit(habit);

        System.out.println(week.getHabit());

    }

}