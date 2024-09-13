package br.com.monitodehabitos.monitodehabitos.application.gateway;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserExeption;

import java.util.List;

public interface HabitRepository {
    Habit save(Habit habit);
    Habit update(Long id, Habit newHabit);
    Habit findById(Long id);
    void delete(Long id);
    List<Habit> findAllByUser(Long userId);
}
