package br.com.monitodehabitos.monitodehabitos.infra.controller.weekDto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ChangeProgressStatusDto(
        @NotBlank(message = "{date.required}")
        @Pattern(
                regexp = "^\\d{2}/\\d{2}/\\d{4}$",
                message = "{date.invalid}"
        )
        String progressDate
) {
}
