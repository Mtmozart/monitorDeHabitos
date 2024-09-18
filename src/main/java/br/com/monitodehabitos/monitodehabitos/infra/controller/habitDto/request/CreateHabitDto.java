package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request;

public record CreateHabitDto(
        Long clientId,
        String description,
        String start
) {
}
