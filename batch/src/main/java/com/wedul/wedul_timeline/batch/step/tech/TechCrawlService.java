package com.wedul.wedul_timeline.batch.step.tech;

import com.wedul.wedul_timeline.batch.step.SiteCrawlerI;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.util.DateUtil;
import com.wedul.wedul_timeline.core.util.HashUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * It/기술 관련 크롤링 서비스
 *
 * @author wedul
 * @since 2019-06-04
 **/
public abstract class TechCrawlService implements SiteCrawlerI {

    @Override
    public String getSourceId(String landingUri) {
        return HashUtil.sha256(landingUri);
    }

    @Override
    public String removeTag(String text) {
        Pattern pattern = Pattern.compile("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", Pattern.DOTALL);
        return pattern.matcher(text).replaceAll("");
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
