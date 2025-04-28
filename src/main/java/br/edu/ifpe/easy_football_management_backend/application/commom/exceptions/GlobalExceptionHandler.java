package br.edu.ifpe.easy_football_management_backend.application.commom.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("handleValidationExceptions: ", ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("handleConstraintViolationException: ", ex);
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(error ->
                errors.put(error.getPropertyPath().toString(), error.getMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        log.error("handleGenericException: ", ex);
        Map<String, String> error = new HashMap<>();
        String message = ex.getMessage();
        Arrays.stream(ex.getStackTrace()).limit(1).forEach(st -> error.put(st.getMethodName(), message));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, String>> handleGenericBusinessException(Exception ex) {
        log.error("handleGenericBusinessException: ", ex);
        Map<String, String> error = new HashMap<>();
        String message = ex.getMessage();
        Arrays.stream(ex.getStackTrace()).limit(1).forEach(st -> error.put(st.getMethodName(), message));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

//    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
//    public ResponseEntity<Map<String, String>> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
//        Map<String, String> error = new HashMap<>();
//        error.put("error", "Recurso não encontrado.");
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//    }

}