package com.wedul.wedul_timeline.batch.tech;

import com.wedul.wedul_timeline.batch.step.tech.feed.impl.VcncTechService;
import com.wedul.wedul_timeline.batch.step.tech.rss.KakaoTechService;
import com.wedul.wedul_timeline.batch.step.tech.rss.ZumInternetTechService;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TechCrawlTest {

    @Inject
    private ZumInternetTechService zumInternetTechService;

    @Inject
    private VcncTechService vcncTechService;

    @Inject
    private KakaoTechService kakaoTechService;

    @Test
    public void crawlZumInternetTech() throws IOException {
        TimeLineSite timeLineSite = TimeLineSite.builder()
                .siteUrl("https://zuminternet.github.io/feed.xml")
                .build();

        List<TimeLineItem> timeLineItems = zumInternetTechService.crawl(timeLineSite);

        Assert.assertTrue(timeLineItems.size() > 0);
    }

    @Test
    public void crawVcncTech() throws IOException {
        TimeLineSite vcncTechSite = TimeLineSite.builder()
                .siteUrl("http://engineering.vcnc.co.kr/atom.xml")
                .build();

        List<TimeLineItem> timeLineItems = vcncTechService.crawl(vcncTechSite);

        Assert.assertTrue(timeLineItems.size() > 0);
    }

    @Test
    public void crawlKakaoTech() throws IOException {
        TimeLineSite kakaoTechSite = TimeLineSite.builder()
                .siteUrl("http://tech.kakao.com/rss/")
                .build();

        List<TimeLineItem> timeLineItems = kakaoTechService.crawl(kakaoTechSite);

        Assert.assertTrue(timeLineItems.size() > 0);
    }

}
