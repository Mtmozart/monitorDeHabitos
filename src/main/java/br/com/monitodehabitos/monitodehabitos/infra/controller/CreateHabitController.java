package br.com.monitodehabitos.monitodehabitos.infra.controller;

import java.time.LocalDate;

public record CreateHabitController(
        Long clientId,
        String description,
        String start
) {
}
