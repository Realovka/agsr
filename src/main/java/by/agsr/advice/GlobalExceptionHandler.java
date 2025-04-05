package by.agsr.advice;

import by.agsr.exception.EntityNotFoundException;
import by.agsr.exception.Message;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Message> handleEntityNotFound(EntityNotFoundException ex) {
        String message = ex.getMessageKey();
        return new ResponseEntity<>(new Message(message), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<String> handleJacksonDeserializationError() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Data format is wrong");
    }
}
