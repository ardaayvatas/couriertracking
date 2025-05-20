package com.ardaayvatas.couriertracking.common.exception;

import com.ardaayvatas.couriertracking.enumeration.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        return buildErrorResponse(ex.getExceptionType(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceCallException.class)
    public ResponseEntity<Object> handleServiceCallException(ServiceCallException ex) {
        return buildErrorResponse(ex.getExceptionType(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        return buildErrorResponse(ExceptionType.UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(message);
    }

    private ResponseEntity<Object> buildErrorResponse(ExceptionType exceptionType, HttpStatus status) {
        Map<String, Object> body = Map.of(
                "timestamp", LocalDateTime.now(),
                "code", exceptionType.getCode(),
                "message", exceptionType.getMessage(),
                "status", status.value()
        );
        return new ResponseEntity<>(body, status);
    }
}
