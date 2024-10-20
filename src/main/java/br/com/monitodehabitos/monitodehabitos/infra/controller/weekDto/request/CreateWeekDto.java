package br.com.monitodehabitos.monitodehabitos.infra.controller.weekDto.request;

public record CreateWeekDto(
        Long habitId,
        Long clientId
) {
}
