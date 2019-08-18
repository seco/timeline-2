package com.wedul.wedul_timeline.batch.step.job.page;

import com.wedul.wedul_timeline.batch.step.job.JobCrawlService;
import com.wedul.wedul_timeline.core.config.error.NotFoundException;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
 * @since 2019-07-22
 **/
@Slf4j
@Service("LineJobService")
public class LineJobService extends JobCrawlService implements PageJobServiceI {

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        List<TimeLineItem> timeLineItems = new ArrayList<>();

        Document document = Jsoup.connect(timeLineSite.getSiteUrl()).get();
        Elements elements = document.getElementsByTag("tr");

        if (null == elements || elements.size() <= 0) throw new NotFoundException("라인 채용정보를 가져오는데 실패하였습니다.");

        elements.forEach(ele -> {
            try {
                String landingUri = ele.selectFirst("a").absUrl("href");
                Document innerHtml = Jsoup.connect(landingUri).get();
                Element element = innerHtml.selectFirst(".content_title_area");

                String title = element.selectFirst("h3").text();
                long date = DateUtil.unixTimeStamp(element.selectFirst(".cont_inf").text().replace(".", "-"));
                String content = Jsoup.clean(innerHtml.selectFirst(".content_area").html(), Whitelist.basic());

                TimeLineItem timeLineItem = TimeLineItem.builder()
                        .sourceId(getSourceId(landingUri))
                        .title(title)
                        .landingUrl(landingUri)
                        .timeLineSite(timeLineSite)
                        .logoUrl(logoUrl(StringUtils.EMPTY))
                        .content(content)
                        .publishedAt(date)
                        .build();

                timeLineItems.add(timeLineItem);
            } catch (Exception e) {
                log.error("라인 상세 본문 페이지 수집에 실패하였습니다.");
            }
        });

        return timeLineItems;
    }

    @Override
    public String logoUrl(String subUrl) {
        return "https://scdn.line-apps.com/n/_s1/_0/linecorpweb-uit/images/logo_h1_v2.png";
    }
}
