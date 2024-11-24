package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request;

import br.com.monitodehabitos.monitodehabitos.domain.entities.HabitStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record CreateHabitDto(
        @NotBlank(message = "{clientId.required}")
        String clientId,

        @NotBlank(message = "{description.required}")
        String description,

        @NotBlank(message = "{status.required}")
        HabitStatus status,

        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "{date.invalid}")
        @NotBlank( message = "{date.invalid}")
        String start,

        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "{date.invalid}")
        @NotBlank( message = "{date.invalid}")
        String end
) {
}
