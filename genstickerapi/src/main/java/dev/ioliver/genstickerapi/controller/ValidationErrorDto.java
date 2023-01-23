package dev.ioliver.genstickerapi.controller;

import org.springframework.validation.BindException;

public record ValidationErrorDto(String message, String campo) {

  public ValidationErrorDto(BindException ex) {
    this(ex.getFieldErrors().get(0).getDefaultMessage(), ex.getFieldErrors().get(0).getField());
  }
}
