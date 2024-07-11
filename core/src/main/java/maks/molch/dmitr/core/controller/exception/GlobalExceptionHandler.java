package maks.molch.dmitr.core.controller.exception;

import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleAlreadyExistException(AlreadyExistException e) {
        return new ResponseEntity<>(ErrorMessage.fromThrowable(e, "Entity already exist!"), HttpStatus.CONFLICT);
    }
}
