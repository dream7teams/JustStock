package net.juststock.trading.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
    var first = ex.getBindingResult().getFieldErrors().stream().findFirst();
    String msg = first.map(f -> f.getField() + ": " + f.getDefaultMessage()).orElse("Validation error");
    return ResponseEntity.badRequest().body(Map.of("success", false, "message", msg));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false, "message", ex.getMessage()));
  }
}
