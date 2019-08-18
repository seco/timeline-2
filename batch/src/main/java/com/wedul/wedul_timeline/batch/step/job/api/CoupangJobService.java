package com.wedul.wedul_timeline.batch.step.job.api;

import com.wedul.wedul_timeline.batch.step.job.dto.CoupangResDto;
import com.wedul.wedul_timeline.core.config.error.NotFoundException;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-08-18
 **/
@Service("CoupangJobService")
@Slf4j
public class CoupangJobService extends ApiJobService {

    public String jobContentClass() {
        return "searched-job-item";
    }

    public String titleClass() {
        return "searched-job-title";
    }

    public String contentClass() {
        return "ats-description";
    }

    public String dateClass() {
        return "job-info--date";
    }

    @Override
    public String logoUrl(String subUrl) {
        return "https://tbcdn.talentbrew.com/company/24450/img/logo/logo-12764-14099.png";
    }

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        List<TimeLineItem> timeLineItems = new ArrayList<>();

        CoupangResDto coupangResDto =
            restTemplate().getForObject(timeLineSite.getSiteUrl(), CoupangResDto.class);
        Elements elements = Jsoup.parse(coupangResDto.getResults()).getElementsByClass(jobContentClass());
        if (null == elements || elements.size() <= 0) throw new NotFoundException("쿠팡 채용정보를 가져오는데 실패하였습니다.");

        elements.forEach(element -> {
            try {
                String landingUrl = landingUrl(element.selectFirst("a").attributes().get("href").split("-/")[1]);
                String title = element.getElementsByClass(titleClass()).get(0).text();

                Document document = Jsoup.connect(landingUrl).get();
                String content = document.getElementsByClass(contentClass()).get(0).html();

                long date = DateUtil.unixTimeStamp(document.getElementsByClass(dateClass()).text().replaceAll("/", "-"));

                TimeLineItem timeLineItem = TimeLineItem.builder()
                    .sourceId(getSourceId(landingUrl))
                    .title(title)
                    .landingUrl(landingUrl)
                    .timeLineSite(timeLineSite)
                    .logoUrl(logoUrl(StringUtils.EMPTY))
                    .content(content)
                    .publishedAt(date)
                    .build();

                timeLineItems.add(timeLineItem);
            } catch (Exception e) {
                log.error("쿠팡 상세 본문 페이지 수집에 실패하였습니다.");
            }
        });

        return timeLineItems;
    }

    @Override
    public String landingUrl(String subUrl) {
        return new StringBuilder("https://rocketyourcareer.kr.coupang.com/직무/-/").append(subUrl).toString();
    }
}
