package com.wedul.wedul_timeline.batch.step.job.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-08-18
 **/
public interface ApiJobServiceI {

  default RestTemplate restTemplate() {
    return new RestTemplate();
  }

  default String landingUrl() {
    return StringUtils.EMPTY;
  }

}
