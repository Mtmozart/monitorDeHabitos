package br.com.monitodehabitos.monitodehabitos.domain;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

class FactoryHabitTest {

    @Test
    void deveriaCriarHabitoComFabriva() throws HabitExeption {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(client,"Descrição genéria de algo", LocalDate.now());
        Assertions.assertEquals("Descrição genéria de algo", habit.getDescription());
        Assertions.assertEquals(LocalDate.now(), habit.getStart());
    }
}