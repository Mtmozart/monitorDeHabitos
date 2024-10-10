package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class HabitTest {

    @Test
    @DisplayName("Should be to change the date start")
    void scenario01() throws HabitExeption {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.now());
        LocalDate newDate = LocalDate.of(2024, 9, 21);
        habit.changeDateStart(newDate);
        Assertions.assertEquals(newDate, habit.getStart());
    }

    @Test
    @DisplayName("Should NOT be to change the the status 'done'")
    void scenario02() throws HabitExeption, WeekException {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.now());
        habit.changeDo(LocalDate.now());
        Assertions.assertEquals(true, habit.getProgress().getFirst().getCompleted());
        Assertions.assertEquals(false, habit.getDone());
    }

    @Test
    @DisplayName("Should be to change the the status 'done' for true")
    void scenario03() throws HabitExeption, WeekException {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.now());
        habit.changeDo(LocalDate.now());
        habit.changeDo(LocalDate.now().plusDays(1));
        Assertions.assertEquals(true, habit.getProgress().getFirst().getCompleted());
        Assertions.assertEquals(true, habit.getProgress().getLast().getCompleted());
        Assertions.assertEquals(true, habit.getDone());
    }
    @Test
    @DisplayName("Should return the end in the next saturday")
    void scenario04() throws HabitExeption {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.parse("2024-09-14"));
        habit.calcEnd(habit.getStart());
        Assertions.assertEquals(LocalDate.of(2024, 9, 21), habit.getEnd());
    }
    @Test
    @DisplayName("Should return 14.33% of percentage for day")
    void scenario05() throws HabitExeption {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.of(2024, 9, 8));
        Assertions.assertEquals(14.29, habit.getPercentageForDay());
    }

    @Test
    @DisplayName("Should return size equal 4 because the list progress until saturday.")
    void scenario06() throws HabitExeption {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.of(2024, 9, 24));
        //O SABÁDO SEGUINTE AO DIA 24/09 É  DIA 27/09.
        Assertions.assertEquals(4, habit.getProgress().size());
    }

    @Test
    @DisplayName("Should return 27/09 with the last day.")
    void scenario07() throws HabitExeption {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.of(2024, 9, 24));
        //O SABÁDO SEGUINTE AO DIA 24/09 É  DIA 27/09.
        Assertions.assertEquals(LocalDate.of(2024, 9, 27), habit.getProgress().get(3).getDate());
    }
}
