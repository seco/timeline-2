package com.wedul.wedul_timeline.batch.step.job.api;

import com.wedul.wedul_timeline.batch.step.job.JobCrawlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-08-18
 **/
public abstract class ApiJobService extends JobCrawlService {

  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public String landingUrl(String subUrl) {
    return StringUtils.EMPTY;
  }

}
