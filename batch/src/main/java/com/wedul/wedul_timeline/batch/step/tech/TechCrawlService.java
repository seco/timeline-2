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

    public TechCrawlService() {

    }

    @Override
    public String getSourceId(String landingUri) {
        return HashUtil.sha256(landingUri);
    }

    @Override
    public String removeTag(String text) {
        Pattern pattern = Pattern.compile("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", Pattern.DOTALL);
        return pattern.matcher(text).replaceAll("");
    }

}
