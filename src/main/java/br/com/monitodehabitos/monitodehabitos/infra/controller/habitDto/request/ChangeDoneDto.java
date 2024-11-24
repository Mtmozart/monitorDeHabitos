package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request;

import br.com.monitodehabitos.monitodehabitos.domain.entities.HabitStatus;
import jakarta.validation.constraints.NotBlank;

public record ChangeDoneDto(
        @NotBlank(message = "{status.required}")
        HabitStatus status
) {
}
