package com.epam.esm.exception;

public class CustomDaoException extends RuntimeException{
  public CustomDaoException() {
  }

  public CustomDaoException(String message, Throwable cause) {
    super(message, cause);
  }
}
