package com.wedul.wedul_timeline.batch.step.tech.rss;

import com.wedul.wedul_timeline.batch.step.tech.TechCrawlService;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.util.DateUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.util.List;

/**
 * RSS XML 기반 기술 블로그 크롤링 서비스
 */
public abstract class RssTechService extends TechCrawlService {

    public RssTechService() {
        super();
    }

    protected void removeTagByKeys(Document document, List<String> removeTagKeys) {
        for (String removeTagKey : removeTagKeys) {
            document.select(removeTagKey).remove();
        }

        Elements pTagElements = document.select("p");
        for (Element pTagElement : pTagElements) {
            if (!pTagElement.hasText() && pTagElement.getElementsByTag("img").size() < 1) {
                pTagElement.remove();
            }
        }
    }

    protected void changeImageTagPath(Elements elements) {
        Elements elems = elements.select("[src]");
        for (Element elem : elems) {
            if (!elem.attr("src").equals(elem.attr("abs:src"))) {
                elem.attr("src", elem.attr("abs:src"));
            }
        }
    }

    protected String partitionContent(Element element, int LIMIT_LINE) {
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < element.childNodes().size(); i++) {
            content.append(element.child(i));

            if (i == LIMIT_LINE) {
                break;
            }
        }

        return content.toString();
    }

    protected TimeLineItem createTimeLineItem(Element ele, TimeLineSite timeLineSite, String logoPngUrl, Elements innerElements) throws ParseException {
        return TimeLineItem.builder()
                .sourceId(getSourceId(ele.select("link").text()))
                .title(ele.select("title").text())
                .publishedAt(DateUtil.convertPubDateToTimestamp(ele.select("pubDate").text()))
                .landingUrl(ele.select("link").text())
                .timeLineSite(timeLineSite)
                .logoUrl(logoPngUrl)
                .content(partitionContent(innerElements.get(0), 5))
                .build();
    }

}