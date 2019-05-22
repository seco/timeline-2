package com.wedul.wedul_timeline.core.config.error;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-22
 **/
public class FobbidenException extends CustomException {

  public FobbidenException() {
    super("권한이 없습니다.", 403);
  }

}
