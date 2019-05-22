package com.wedul.wedul_timeline.core.config.error;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-22
 **/
public class BadRequestException extends CustomException {

  public BadRequestException() {
    super("잘못된 요청입니다.", 500);
  }
}
