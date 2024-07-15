package maks.molch.dmitr.core.controller.exception;

import maks.molch.dmitr.core.service.exception.*;
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

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(ErrorMessage.fromThrowable(e, "Entity not found!"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            AuthenticationException.class,
            AuthorizationException.class,
            AccessDeniedException.class,
    })
    public ResponseEntity<ErrorMessage> handleUnauthorizedHttpException(Exception e) {
        var message = switch (e) {
            case AuthorizationException ignored -> "Not allowed resource!";
            case AccessDeniedException ignored -> "Jwt token is expired!";
            case AuthenticationException ignored -> "Authentication error!";
            default -> "Access denied!";
        };
        return new ResponseEntity<>(ErrorMessage.fromThrowable(e, message), HttpStatus.UNAUTHORIZED);
    }
}
