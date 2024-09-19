package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request;

public record UpdateHabitDto(
        String description,
        String start
) {
}
