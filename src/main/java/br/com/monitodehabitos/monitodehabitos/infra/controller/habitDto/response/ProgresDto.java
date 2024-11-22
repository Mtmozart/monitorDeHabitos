package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.response;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.ProgressEnumStatus;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;

import java.time.LocalDate;

public record ProgresDto(
        LocalDate currentDate,
        ProgressEnumStatus completed
) {

}
