package com.wedul.wedul_timeline.batch.tech;

import com.wedul.wedul_timeline.batch.job.timelineItem.TimeLineItemJobConfiguration;
import com.wedul.wedul_timeline.batch.step.tech.ZumInternetTechService;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {"job.name=" + TimeLineItemJobConfiguration.JOB_NAME})
public class TechCrawlTest {

    @Inject
    private ZumInternetTechService zumInternetTechService;

    @Test
    public void crawlZumInternetTech() throws IOException {
        TimeLineSite timeLineSite = TimeLineSite.builder()
                .siteUrl("https://zuminternet.github.io/feed.xml")
                .build();

        List<TimeLineItem> timeLineItems = zumInternetTechService.crawl(timeLineSite);

        Assert.assertTrue(timeLineItems.size() > 0);
    }

}
