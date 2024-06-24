package fr.epita.discover.presentation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 24/06/2024
 */

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleUserNotFound(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
