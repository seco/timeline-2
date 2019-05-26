package com.wedul.wedul_timeline.core.config.error;

import lombok.Data;
import org.springframework.http.ResponseEntity;

/**
 * Custome Eexction 클래스
 *
 * @author wedul
 * @since 2019-05-22
 **/
@Data
public class CustomException extends RuntimeException {

  private final int code;

  protected CustomException(String message, int code) {
    super(message);
    this.code = code;
  }

  public ResponseEntity<ErrorResponse> errorResponse() {
    return ResponseEntity.status(this.code).body(ErrorResponse.builder().errCode(this.code).message(this.getMessage()).build());
  }

}
