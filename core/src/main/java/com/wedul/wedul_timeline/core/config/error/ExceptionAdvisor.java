package com.wedul.wedul_timeline.core.config.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-22
 **/
@RestControllerAdvice
public class ExceptionAdvisor extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity customExceptionHandler(CustomException exception) {
    return exception.errorResponse();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity exceptionHandler(Exception exception) {
    return ResponseEntity.status(504).body(ErrorResponse.builder().errCode(504).message(exception.getMessage()).build());
  }

}
