package com.epam.esm.exception;

public class ResourceNotFoundException extends RuntimeException{
  public ResourceNotFoundException() {
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
