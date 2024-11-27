package br.com.monitodehabitos.monitodehabitos.domain.exception;

import java.time.LocalDateTime;

public class ProgressException extends Exception{
    private LocalDateTime errorDate;

    public ProgressException(String message) {
        super(message);
        this.errorDate = LocalDateTime.now();
    }

    public LocalDateTime getErrorDate() {
        return errorDate;
    }
}
