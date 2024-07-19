package maks.molch.dmitr.core.controller.exception;

import maks.molch.dmitr.core.service.exception.AccessDeniedException;
import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import maks.molch.dmitr.core.service.exception.AuthenticationException;
import maks.molch.dmitr.core.service.exception.AuthorizationException;
import maks.molch.dmitr.core.service.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = AlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleAlreadyExistException(AlreadyExistException e) {
        return new ResponseEntity<>(ErrorMessage.fromThrowable(e, "Entity already exist!"), HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(ErrorMessage.fromThrowable(e, "Entity not found!"), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                ErrorMessage.fromThrowable(e, "Argument not valid!"),
                HttpStatus.BAD_REQUEST
        );
    }
}
