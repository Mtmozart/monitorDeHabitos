package br.com.monitodehabitos.monitodehabitos.infra.infras.excetions;


import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.ProgressException;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserException;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseError> handleError404(EntityNotFoundException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleError400(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = ex.getFieldErrors().stream()
                .map(ValidationError::new)
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> handleError400(HttpMessageNotReadableException ex) {
            ResponseError responseError = new ResponseError(
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now()
            );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleError500(Exception ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseError> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseError> handleAccessDeniedException(AccessDeniedException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.FORBIDDEN,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseError);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<ResponseError> handleConstraintViolationException(jakarta.validation.ConstraintViolationException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(java.util.NoSuchElementException.class)
    public ResponseEntity<ResponseError> handleNoSuchElementException(java.util.NoSuchElementException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseError> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseError> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.CONFLICT,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseError);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseError> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.METHOD_NOT_ALLOWED,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(responseError);
    }

    // Tratamento para HabitExeption
    @ExceptionHandler(HabitExeption.class)
    public ResponseEntity<ResponseError> handleHabitException(HabitExeption ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now() // Usando a data do erro atual
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    // Tratamento para ProgressException
    @ExceptionHandler(ProgressException.class)
    public ResponseEntity<ResponseError> handleProgressException(ProgressException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ex.getErrorDate() // Usando a data do erro da ProgressException
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    // Tratamento para UserException
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseError> handleUserException(UserException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ex.getErrorDate() // Usando a data do erro da UserException
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    // Tratamento para WeekException
    @ExceptionHandler(WeekException.class)
    public ResponseEntity<ResponseError> handleWeekException(WeekException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ex.getErrorDate() // Usando a data do erro da WeekException
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    private record ValidationError(String campo, String mensagem, LocalDateTime date) {
        public ValidationError(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage(), LocalDateTime.now());
        }
    }
}
