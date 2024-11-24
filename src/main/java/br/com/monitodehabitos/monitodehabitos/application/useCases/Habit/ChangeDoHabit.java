package br.com.monitodehabitos.monitodehabitos.application.useCases.Habit;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.HabitStatus;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.time.LocalDate;
import java.util.UUID;

public class ChangeDoHabit {
    private final HabitRepository habitRepository;

    public ChangeDoHabit(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public Habit changeDoHabit(String id, HabitStatus status) throws HabitExeption, WeekException {
        return habitRepository.changeDone(id, status);
    }
}
