package br.com.monitodehabitos.monitodehabitos.domain.factories;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FactoryWeekTest {

    @Test
    @DisplayName("Should to create week with id, client and habit")
    void scenario01() throws HabitExeption {
        FactoryWeek factoryWeek = new FactoryWeek();
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo", LocalDate.now());

        Week week = factoryWeek.createWeekWithIdHabitClient(1L, habit, client);
        Assertions.assertEquals(1L, week.getId());

    }
}