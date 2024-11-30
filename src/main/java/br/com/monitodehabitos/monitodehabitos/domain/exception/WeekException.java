package br.com.monitodehabitos.monitodehabitos.domain.exception;

import java.time.LocalDateTime;

public class WeekException extends Exception{
    private LocalDateTime errorDate;

    public WeekException(String message) {
        super(message);
        this.errorDate = LocalDateTime.now();
    }

    public LocalDateTime getErrorDate() {
        return errorDate;
    }
}
