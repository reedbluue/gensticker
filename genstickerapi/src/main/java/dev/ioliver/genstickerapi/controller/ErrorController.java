package dev.ioliver.genstickerapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestControllerAdvice
public class ErrorController {

  @ExceptionHandler({MissingServletRequestParameterException.class, MissingServletRequestPartException.class})
  public ResponseEntity<String> erroDeParametro() {
    return ResponseEntity.badRequest().body("Parâmetros inválidos!");
  }
}
