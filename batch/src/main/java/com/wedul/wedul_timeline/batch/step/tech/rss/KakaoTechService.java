package com.wedul.wedul_timeline.batch.step.tech.rss;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("KakaoTechService")
@Slf4j
public class KakaoTechService extends RssTechService {

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        List<TimeLineItem> timeLineItems = new ArrayList<>();

        Document doc = Jsoup.connect(timeLineSite.getSiteUrl()).get();
        Elements elements = doc.select("channel").select("item");

        elements.forEach(ele -> {
            try {
                Document innerDoc = Jsoup.connect(ele.select("link").text()).get();

                Elements innerElements = innerDoc.select("div#post-content");
                changeImageTagPath(innerElements);

                timeLineItems.add(createTimeLineItem(ele, timeLineSite, getLogoUrl(), innerElements));
            } catch (Exception e) {
                log.error("카카오 테크 페이지 수집에 실패하였습니다", e);
            }
        });

        return timeLineItems;
    }

    @Override
    public String getLogoUrl() {
        return "https://careers.kakao.com/images/logo/logo_dk.png?v=190808023704";
    }
}
