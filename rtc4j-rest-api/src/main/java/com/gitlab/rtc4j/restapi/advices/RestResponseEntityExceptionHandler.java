package com.gitlab.rtc4j.restapi.advices;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<Object> handleElementNotFoundInThePersistenceLayer() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<Object> handleRelatedElementNotFoundInThePersistenceLayer() {
    return ResponseEntity.badRequest().build();
  }
}
