package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
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
    @DisplayName("Should be to change the the status 'done'")
    void scenario02() throws HabitExeption {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.now());
        habit.changeDo();
        Assertions.assertEquals(true, habit.getDone());
    }

    @Test
    @DisplayName("Should return the end in the next saturday")
    void scenario03() throws HabitExeption {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.parse("2024-09-14"));
        habit.calcEnd(habit.getStart());
        Assertions.assertEquals(LocalDate.of(2024, 9, 21), habit.getEnd());
    }
    @Test
    @DisplayName("Should return 14.33% of percentage for day")
    void scenario04() throws HabitExeption {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.of(2024, 9, 8));
        Assertions.assertEquals(14.29, habit.getPercentageForDay());
    }
}
