package com.wedul.wedul_timeline.batch.step.job;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-07-22
 **/
@Slf4j
public class LineJobService  extends JobCrawlService {

    private final String LOGO_URL = "https://scdn.line-apps.com/n/_s1/_0/linecorpweb-uit/images/logo_h1_v2.png";

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        return null;
    }
}
