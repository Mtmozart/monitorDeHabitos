package br.com.monitodehabitos.monitodehabitos.infra.infras.excetions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ResponseError(String message, HttpStatus httpStatus, LocalDateTime now) {
}
