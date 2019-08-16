package com.wedul.wedul_timeline.batch.step.tech.feed.impl;

import com.wedul.wedul_timeline.batch.step.tech.feed.FeedTechService;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("VcncTechService")
@Slf4j
public class VcncTechService extends FeedTechService {

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        List<TimeLineItem> timeLineItems = new ArrayList<>();

        Document doc = Jsoup.connect(timeLineSite.getSiteUrl()).get();
        Elements elements = doc.select("entry");

        elements.forEach(ele -> {
            try {
                TimeLineItem timeLineItem = TimeLineItem.builder()
                        .sourceId(getSourceId(ele.select("link").attr("href")))
                        .title(ele.select("title").text())
                        .publishedAt(DateUtil.convertAtomDateToTimestamp(ele.select("updated").text()))
                        .landingUrl(ele.select("link").attr("href"))
                        .timeLineSite(timeLineSite)
                        .logoUrl(getLogoUrl())
                        .content(ele.select("content").text())
                        .build();

                timeLineItems.add(timeLineItem);
            } catch (Exception e) {
                log.error("VCNC 테크 페이지 수집에 실패하였습니다", e);
            }
        });

        return timeLineItems;
    }

    @Override
    public String getLogoUrl() {
        return "https://image.rocketpunch.com/company/30/vcnc_logo_1559872041.jpg?s=400x400&t=inside";
    }
}