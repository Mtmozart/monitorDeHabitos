package br.com.monitodehabitos.monitodehabitos.application.gateway;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.HabitStatus;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.time.LocalDate;
import java.util.List;

public interface HabitRepository {
    Habit save(Habit habit);

    Habit update(String id, Habit newHabit) throws HabitExeption;

    Habit findById(String id) throws HabitExeption;

    void delete(String id) throws HabitExeption;

    Habit changeDone(String id, HabitStatus status) throws HabitExeption, WeekException;

    List<Habit> findAllByUser(String userId);
}
