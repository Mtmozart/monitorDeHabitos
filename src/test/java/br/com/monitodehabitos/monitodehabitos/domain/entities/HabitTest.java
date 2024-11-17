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
    @DisplayName("Should NOT be to change the the status 'done'")
    void scenario02() throws HabitExeption, WeekException {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.now(), LocalDate.now());
        habit.changeStatus(HabitStatus.IN_PROGRESS);
        Assertions.assertEquals(HabitStatus.IN_PROGRESS, habit.getDone());
    }

}
