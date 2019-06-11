package com.wedul.wedul_timeline.batch.job;

import com.wedul.wedul_timeline.batch.job.timelineItem.TimeLineItemJobConfiguration;
import com.wedul.wedul_timeline.batch.service.step.job.WoowahanJobService;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {"job.name=" + TimeLineItemJobConfiguration.JOB_NAME})
public class JobCrawlTest {

  @Autowired
  WoowahanJobService woowahanService;

  @Test
  public void title_valid() throws IOException {
    TimeLineSite timeLineSite = TimeLineSite.builder().siteUrl("https://www.woowahan.com/jobapi/jobs/list?searchword=w011600&cc=").build();

    woowahanService.crawl(timeLineSite);
  }
}
