package com.wedul.wedul_timeline.batch.job;

import com.wedul.wedul_timeline.batch.step.job.JobCrawlService;
import com.wedul.wedul_timeline.batch.step.job.locketPunch.MyRealTripJobService;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-08-05
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyRealTripJobServiceTest {

    private JobCrawlService myRealTripService;

    @Before
    public void setup() {
        this.myRealTripService = new MyRealTripJobService();
    }

    @Test
    public void 마이리얼트립_정보가져오기() throws IOException {
        myRealTripService.crawl(TimeLineSite.builder().siteUrl("https://www.rocketpunch.com/companies/myrealtrip/jobs").build());
    }

}
