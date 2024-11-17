package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateHabitDto(
        @NotNull(message = "{clientId.required}")
        Long clientId,

        @NotBlank(message = "{description.required}")
        String description,

        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "{date.invalid}")
        String start,

        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "{date.invalid}")
        String end
) {
}
