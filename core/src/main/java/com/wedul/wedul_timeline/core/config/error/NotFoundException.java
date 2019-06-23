package com.wedul.wedul_timeline.core.config.error;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-22
 **/
public class NotFoundException extends CustomException {

  public NotFoundException() {
    super("잘못된 페이지 입니다.", 404);
  }

  public NotFoundException(String exMsg) {
    super(exMsg, 404);
  }

}
