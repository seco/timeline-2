package com.wedul.wedul_timeline.batch.job;

import com.wedul.wedul_timeline.batch.step.job.JobCrawlService;
import com.wedul.wedul_timeline.batch.step.job.LineJobService;
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
 * @since 2019-07-26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LineJobServiceTest {

    private JobCrawlService lineJobService;

    @Before
    public void setup() {
        this.lineJobService = new LineJobService();
    }

    @Test
    public void 라인_태그정보가져오기() throws IOException {
        lineJobService.crawl(TimeLineSite.builder().siteUrl("https://recruit.linepluscorp.com/lineplus/career/list?classId=148").build());
    }

}
