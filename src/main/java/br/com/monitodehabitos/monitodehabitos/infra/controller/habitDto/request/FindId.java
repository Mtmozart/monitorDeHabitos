package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request;

import jakarta.validation.constraints.NotNull;

public record FindId(

        @NotNull(message = "{habitId.required}")
        Long id
) {
}
