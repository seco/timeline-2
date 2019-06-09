package com.wedul.wedul_timeline.batch.service.job;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-09
 **/
@Slf4j
@Service
public class DngnTeamService extends JobCrawlService {

  @Override
  public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {

    Document doc = Jsoup.connect(timeLineSite.getSiteUrl()).get();

    Element title = doc.selectFirst("[data-root=true]");
    log.info(title.text());

    return null;
  }

  @Override
  String title() {
    return null;
  }

  @Override
  String content() {
    return null;
  }

  @Override
  String mainImageUrl() {
    return null;
  }
}
