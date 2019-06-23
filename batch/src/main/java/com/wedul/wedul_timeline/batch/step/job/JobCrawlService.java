package com.wedul.wedul_timeline.batch.step.job;

import com.wedul.wedul_timeline.batch.step.SiteCrawlerI;
import com.wedul.wedul_timeline.core.util.HashUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 채용 관련 크롤링 서비스
 *
 * @author wedul
 * @since 2019-06-04
 **/
public abstract class JobCrawlService implements SiteCrawlerI {

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
