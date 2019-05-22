package com.wedul.wedul_timeline.core.config.error;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-22
 **/
public class CustomException extends Exception {

  private int code;

  public CustomException(String message, int code) {
    super(message);
    this.code = code;
  }

}
