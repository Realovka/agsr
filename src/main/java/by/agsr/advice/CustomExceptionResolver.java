package by.agsr.advice;

import by.agsr.exception.EntityNotFoundException;
import by.agsr.exception.Message;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionResolver {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Message> handleEntityNotFound(EntityNotFoundException ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>(new Message(message), HttpStatus.NOT_FOUND);
    }
}
