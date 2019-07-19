package com.wedul.wedul_timeline.batch.step.tech;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("ZumInternetTechService")
@Slf4j
public class ZumInternetTechService extends TechCrawlService {

    private static final String LOGO_PNG_URL = "http://lego.zumst.com/resources/current/images/img_logo_2x_20190604.png";

    private List<String> REMOVE_TAG_KEYS = Arrays.asList("p.post-info", "h1.post-title");

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        List<TimeLineItem> timeLineItems = new ArrayList<>();

        Document doc = Jsoup.connect(timeLineSite.getSiteUrl()).get();
        Elements elements = doc.select("channel").select("item");

        elements.forEach(ele -> {
            try {
                Document innerDoc = Jsoup.connect(ele.select("link").text()).get();
                removeTagByKeys(innerDoc, REMOVE_TAG_KEYS);

                Elements innerElements = innerDoc.select("#post.post-content");
                changeImageTagPath(innerElements);

                timeLineItems.add(createTimeLineItem(ele, timeLineSite, LOGO_PNG_URL, innerElements));
            } catch (Exception e) {
                log.error("줌 인터넷 테크 페이지 수집에 실패하였습니다.", e);
            }
        });

        return timeLineItems;
    }

}
