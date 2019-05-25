package com.wedul.wedul_timeline.core.config.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-22
 **/
@RestControllerAdvice
public class ExceptionAdvisor {

  @Data
  @Builder
  private static class ErrorResponse {
    private int errCode;
    private String message;
  }

  @ExceptionHandler(value = CustomException.class)
  public CustomException customExceptionHandler(CustomException exception) {
    return exception;
  }

  @ExceptionHandler(value = Exception.class)
  public ErrorResponse exceptionHandler(Exception exception) {
    return ErrorResponse.builder().errCode(exception.hashCode()).message(exception.getMessage()).build();
  }
}
