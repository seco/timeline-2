package com.wedul.wedul_timeline.batch.step.job;

import com.wedul.wedul_timeline.core.config.error.NotFoundException;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 로켓 펀치에서 제공하는 채용 서비스 추상 클래스
 *
 * @author wedul
 * @since 2019-08-05
 **/
@Slf4j
public abstract class LocketPunchJobService extends JobCrawlService {

    protected String timeLineItemJobClass() {
        return ".ui.segment.items";
    }

    protected String timeLineItemTitleClass() {
        return "job-title";
    }

    protected String timeLineItemPublishedAtClass() {
        return "job-dates";
    }

    protected String timeLineItemJobContentId() {
        return "job-content";
    }

    protected long timeLineItemPublishedAt(String date) throws ParseException {
        Pattern p = Pattern.compile("[0-9]{2}\\/[0-9]{2}");
        Matcher m = p.matcher(date);
        Calendar cal = Calendar.getInstance();
        return DateUtil.startUnixTimeStamp(m.find() ? new StringBuilder()
                .append(cal.get(Calendar.YEAR))
                .append("-")
                .append(m.group().replace("/", "-"))
                .toString() : DateUtil.todayDate());

    }

    protected String getContent(String detailUrl) throws IOException {
        Document document = Jsoup.connect(detailUrl).get();
        return document.getElementById(timeLineItemJobContentId()).html();
    }

    protected abstract String getLogoUrl();

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        List<TimeLineItem> timeLineItems = new ArrayList<>();

        Document document = Jsoup.connect(timeLineSite.getSiteUrl()).get();
        Elements elements = document.select(timeLineItemJobClass());

        if (null == elements || elements.size() <= 0) throw new NotFoundException("로켓 펀치 채용 정보를 가져오는데 실패하였습니다.");

        elements.forEach(ele -> {
            try {
                Elements element = ele.getElementsByClass(timeLineItemTitleClass());
                String title = element.text();

                String detailUrl = element.get(0).selectFirst("a").absUrl("href");
                long date = timeLineItemPublishedAt(ele.getElementsByClass(timeLineItemPublishedAtClass()).text());

                TimeLineItem timeLineItem = TimeLineItem.builder()
                        .sourceId(getSourceId(detailUrl))
                        .title(title)
                        .landingUrl(detailUrl)
                        .logoUrl(getLogoUrl())
                        .content(getContent(detailUrl))
                        .publishedAt(date)
                        .build();

                timeLineItems.add(timeLineItem);
            } catch (Exception e) {
                log.error("로켓 펀치 채용 정보를 가져오는데 실패하였습니다.");
            }
        });

        return timeLineItems;
    }

}