package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.response;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.HabitStatus;
import br.com.monitodehabitos.monitodehabitos.infra.controller.weekDto.request.CreateWeekDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record ResponseHabitDto(
        Long id,
        String description,
        HabitStatus done,
        LocalDate start,
        LocalDate end,
        List<WeekDto> weeks

) {
    public ResponseHabitDto(Habit habit) {
        this(
        habit.getId(),
        habit.getDescription(),
        habit.getDone(),
        habit.getStart(),
        habit.getEnd(),
               null
        );
    }
}
