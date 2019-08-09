package com.wedul.wedul_timeline.batch.step.tech;

import com.wedul.wedul_timeline.batch.step.tech.rss.RssTechService;
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

import static com.wedul.wedul_timeline.core.type.CompanyType.WOOWAHAN;

@Service("WoowahanTechService")
@Slf4j
public class WoowahanTechService extends RssTechService {


    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        List<TimeLineItem> timeLineItems = new ArrayList<>();

        Document doc = Jsoup.connect(timeLineSite.getSiteUrl()).get();
        Elements elements = doc.select("channel").select("item");

        elements.forEach(ele -> {
            try {
                Document innerDoc = Jsoup.connect(ele.select("link").text()).get();

                Elements innerElements = innerDoc.select("div.post-content");
                changeImageTagPath(innerElements);

                timeLineItems.add(createTimeLineItem(ele, timeLineSite, WOOWAHAN.getLogoUrl(), innerElements));
            } catch (Exception e) {
                log.error("우아한형제들 테크 페이지 수집에 실패하였습니다", e);
            }
        });

        return timeLineItems;
    }

}
