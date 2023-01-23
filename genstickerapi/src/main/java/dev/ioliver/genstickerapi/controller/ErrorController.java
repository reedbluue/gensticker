package dev.ioliver.genstickerapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestControllerAdvice
public class ErrorController {
  @ExceptionHandler(BindException.class)
  public ResponseEntity<ValidationErrorDto> erroDeValidacao(BindException ex) {
    return ResponseEntity.badRequest().body(new ValidationErrorDto(ex));
  }
}
