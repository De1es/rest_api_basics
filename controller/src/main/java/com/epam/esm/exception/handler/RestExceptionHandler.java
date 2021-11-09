package com.epam.esm.exception.handler;

import com.epam.esm.exception.CustomDaoException;
import com.epam.esm.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The RestExceptionHandler class
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * @param ex ResourceNotFoundException
   * @return ResponseEntity with error info
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
    apiError.setMessage(ex.getMessage());
    apiError.setErrorCode("40401");
    return buildResponseEntity(apiError);
  }

  /**
   * @param ex CustomDaoException
   * @return ResponseEntity with error info
   */
  @ExceptionHandler(CustomDaoException.class)
  protected ResponseEntity<Object> handleCustomDaoException(CustomDaoException ex) {
    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
    apiError.setMessage(ex.getMessage());
    apiError.setErrorCode("40402");
    return buildResponseEntity(apiError);
  }


  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
