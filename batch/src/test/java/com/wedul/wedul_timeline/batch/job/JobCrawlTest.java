package com.wedul.wedul_timeline.batch.job;

import com.wedul.wedul_timeline.batch.job.timelineItem.TimeLineItemJobConfiguration;
import com.wedul.wedul_timeline.batch.step.job.DngnJobService;
import com.wedul.wedul_timeline.batch.step.job.KakaoJobService;
import com.wedul.wedul_timeline.batch.step.job.WoowahanJobService;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@RequiredArgsConstructor
@TestPropertySource(properties = {"job.name=" + TimeLineItemJobConfiguration.JOB_NAME})
public class JobCrawlTest {

  private WoowahanJobService woowahanService;
  private KakaoJobService kakaoJobService;
  private DngnJobService dngnJobService;

  @Test
  public void title_valid() throws IOException {
    TimeLineSite timeLineSite = TimeLineSite.builder().siteUrl("https://www.woowahan.com/jobapi/jobs/list?searchword=w011600&cc=").build();

    woowahanService.crawl(timeLineSite);
  }

  @Test
  public void kakao_service() throws Exception {
    TimeLineSite timeLineSite = TimeLineSite.builder().siteUrl("https://careers.kakao.com/jobs").build();

    kakaoJobService.crawl(timeLineSite);
  }

  @Test
  public void dngn_service() {
    dngnJobService.crawl(TimeLineSite.builder().siteUrl("https://careers.kakao.com/jobs").build());
  }
}
