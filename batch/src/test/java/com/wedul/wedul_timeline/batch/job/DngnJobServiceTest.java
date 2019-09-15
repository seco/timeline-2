package com.wedul.wedul_timeline.batch.job;

import com.wedul.wedul_timeline.batch.step.job.api.DngnJobService;
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
 * @since 2019-08-18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DngnJobServiceTest {

    private DngnJobService dngnJobService;

    @Before
    public void setUp() {
        this.dngnJobService = new DngnJobService();
    }

    @Test
    public void dngn_test() throws IOException {
        TimeLineSite timeLineSite = TimeLineSite.builder().siteUrl("https://www.notion.so/api/v3/getRecordValues").build();

        List<TimeLineItem> timeLineItemList = dngnJobService.crawl(timeLineSite);
        assertThat(timeLineItemList.size(), not(0));
    }


}
