package com.wedul.wedul_timeline.api.config;

import com.wedul.wedul_timeline.core.config.error.ErrorResponse;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-26
 **/
@RestController
public class BasicErrorController extends AbstractErrorController {

  public BasicErrorController(ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  @RequestMapping(value = "/error")
  public ResponseEntity handleError() {
    return ResponseEntity.status(404).body(ErrorResponse.builder().errCode(404).message("잘못된 페이지 요청입니다."));
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
