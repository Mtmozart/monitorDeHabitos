package br.com.monitodehabitos.monitodehabitos.infra.controller.weekDto.request;

import java.util.UUID;

public record CreateWeekDto(
        String habitId,
        String clientId
) {
}
