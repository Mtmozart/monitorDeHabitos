package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request;

import br.com.monitodehabitos.monitodehabitos.domain.entities.HabitStatus;

public record UpdateHabitDto(
        String description,
        HabitStatus done
) {
}
