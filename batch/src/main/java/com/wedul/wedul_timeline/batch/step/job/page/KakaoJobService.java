package com.wedul.wedul_timeline.batch.step.job.page;

import com.wedul.wedul_timeline.batch.step.job.JobCrawlService;
import com.wedul.wedul_timeline.core.config.error.NotFoundException;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-21
 **/
@Service("KakaoJobService")
@Slf4j
public class KakaoJobService extends JobCrawlService implements PageJobServiceI {

    private final int MAX_PAGE = 10;

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        List<TimeLineItem> timeLineItems = new ArrayList<>();

        for (int i = 0; i < MAX_PAGE; i++) {
            StringBuilder pageUrl = new StringBuilder(timeLineSite.getSiteUrl())
                    .append("?page=")
                    .append(i);
            Document doc = Jsoup.connect(pageUrl.toString()).get();
            Elements elements = doc.select(".link_notice");

            if (null == elements || elements.size() <= 0) throw new NotFoundException("카카오톡 채용 정보를 가져오지 못했습니다.");

            elements.forEach(ele -> {
                try {
                    Document innerHtml = Jsoup.connect(ele.absUrl("href")).get();
                    TimeLineItem timeLineItem = TimeLineItem.builder()
                            .sourceId(getSourceId(ele.absUrl("href")))
                            .title(ele.text())
                            .landingUrl(ele.absUrl("href"))
                            .timeLineSite(timeLineSite)
                            .logoUrl(logoUrl(innerHtml.selectFirst(".img_logo").attr("src")))
                            .content(Jsoup.clean(innerHtml.selectFirst(".area_cont").html(), Whitelist.basic()))
                            .build();

                    timeLineItems.add(timeLineItem);
                } catch (Exception e) {
                    log.error("카카오 상세 본문 페이지 수집에 실패하였습니다.");
                }

            });
        }
        return timeLineItems;
    }

    @Override
    public String logoUrl(String subUrl) {
        return new StringBuilder("https://careers.kakao.com").append(subUrl).toString();
    }
}
