package com.wedul.wedul_timeline.batch.step.job;

import com.wedul.wedul_timeline.core.config.error.NotFoundException;
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

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-07-22
 **/
@Slf4j
@Service
public class LineJobService  extends JobCrawlService {

    private final String LOGO_URL = "https://scdn.line-apps.com/n/_s1/_0/linecorpweb-uit/images/logo_h1_v2.png";

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        List<TimeLineItem> timeLineItems = new ArrayList<>();

        Document document = Jsoup.connect(timeLineSite.getSiteUrl()).get();
        Elements element = document.getElementsByTag("tbody");

        if (null == element || element.size() <= 0) throw new NotFoundException("라인 채용정보를 가져오는데 실패하였습니다.");


        return null;
    }
}
