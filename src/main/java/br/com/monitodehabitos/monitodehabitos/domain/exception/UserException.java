package br.com.monitodehabitos.monitodehabitos.domain.exception;

import java.time.LocalDateTime;

public class UserException extends Exception {
    private LocalDateTime errorDate;

    public UserException(String message) {
        super(message);
        this.errorDate = LocalDateTime.now();
    }

    public LocalDateTime getErrorDate() {
        return errorDate;
    }
}
