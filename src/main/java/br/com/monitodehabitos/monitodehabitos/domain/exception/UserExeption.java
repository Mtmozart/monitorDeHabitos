package br.com.monitodehabitos.monitodehabitos.domain.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserExeption extends Exception {
    private LocalDateTime errorDate;
    public UserExeption(String message) {
        super(message);
        this.errorDate = LocalDateTime.now();
    }

    public LocalDateTime getErrorDate() {
        return errorDate;
    }
}
