package br.com.monitodehabitos.monitodehabitos.domain.factories;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class FactoryHabitTest {
    @Test
    @DisplayName("Should be to create habit")
    void sceraio01() throws HabitExeption {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "Descrição genéria de algo",  LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 30));
        Assertions.assertEquals("Descrição genéria de algo", habit.getDescription());
        Assertions.assertEquals(LocalDate.of(2024, 11, 1), habit.getStart());
    }

    @Test
    @DisplayName("Should return error when adding habit - Client is null")
    void scenarioErrorClientNull() {
        FactoryHabit factoryHabit = new FactoryHabit();

        HabitExeption exception = Assertions.assertThrows(
                HabitExeption.class,
                () -> {
                    factoryHabit.withDescriptionAndDate(1L, null, "description", LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 30));
                }
        );
        Assertions.assertEquals("Cliente não pode ser nulo", exception.getMessage());
    }
    @Test
    @DisplayName("Should return error when adding habit - Description is null")
    void scenarioErrorDescriptionNull() {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();

        HabitExeption exception = Assertions.assertThrows(
                HabitExeption.class,
                () -> {
                    factoryHabit.withDescriptionAndDate(1L, client, null,LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 30));
                }
        );
        Assertions.assertEquals("Descrição do hábito não pode ser nula", exception.getMessage());
    }
    @Test
    @DisplayName("Should return error when adding habit - Start date is null")
    void scenarioErrorStartNull() {
        Client client = Mockito.mock(Client.class);
        FactoryHabit factoryHabit = new FactoryHabit();
        HabitExeption exception = Assertions.assertThrows(
                HabitExeption.class,
                () -> {
                    factoryHabit.withDescriptionAndDate(1L, client, "description", null, null);
                }
        );
        Assertions.assertEquals("Data de início do hábito não pode ser nula", exception.getMessage());
    }
}
