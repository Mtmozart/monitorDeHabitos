package br.com.monitodehabitos.monitodehabitos.application.gateway;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

import java.util.List;

public interface HabitRepository {
    Habit save(Habit habit);

    Habit update(Long id, Habit newHabit) throws HabitExeption;

    Habit findById(Long id) throws HabitExeption;

    void delete(Long id) throws HabitExeption;

    Habit changeDone(Long id) throws HabitExeption;

    List<Habit> findAllByUser(Long userId);
}
