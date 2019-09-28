package com.wedul.wedul_timeline.batch.job;

import com.wedul.wedul_timeline.batch.step.job.JobCrawlService;
import com.wedul.wedul_timeline.batch.step.job.api.ElevenStJobService;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-08-03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElevenStServiceTest {

    private JobCrawlService elevenStJobService;

    @Before
    public void setup() {
        this.elevenStJobService = new ElevenStJobService();
    }

    @Test
    public void 우아한_형제들_채용_정보가져오기() throws IOException {
        List<TimeLineItem> timeLineItems = elevenStJobService.crawl(TimeLineSite.builder().siteUrl("https://careers.11stcorp.com/jobs/list?announcement.title=&announcement.localYN=&announcement.pageIndex=0&isIng=true&announcement.pageSize=100").build());
        assertThat(timeLineItems.size(), not(0));
    }

}
