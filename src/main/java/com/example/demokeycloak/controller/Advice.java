package com.example.demokeycloak.controller;

import java.util.Map;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Advice {

  @ExceptionHandler(AccessDeniedException.class)
  public Object handleAccessDeniedException(AccessDeniedException e) {
    return Map.of(
        "message", "Access denied",
        "error", e.getLocalizedMessage()
    );
  }
}
