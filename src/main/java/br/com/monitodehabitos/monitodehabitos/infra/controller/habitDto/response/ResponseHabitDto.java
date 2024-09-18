package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.response;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;

import java.time.LocalDate;

public record ResponseHabitDto(
        Long id,
        String description,
        Boolean done,
        LocalDate start,
        LocalDate end
) {
    public ResponseHabitDto(Habit habit) {
        this(
        habit.getId(),
        habit.getDescription(),
        habit.getDone(),
        habit.getStart(),
        habit.getEnd()
        );
    }
}
