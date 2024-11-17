package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request;

import jakarta.validation.constraints.Pattern;

public record ChangeDoneDto(
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "{date.invalid}")
        String date
) {
}
