package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.response;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record ResponseHabitDto(
        Long id,
        String description,
        Boolean done,
        LocalDate start,
        LocalDate end,
        List<ProgresDto> progress

) {
    public ResponseHabitDto(Habit habit) {
        this(
        habit.getId(),
        habit.getDescription(),
        null,
        habit.getStart(),
        habit.getEnd(),
               null
        );
    }
}
