package com.wedul.wedul_timeline.batch.step;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;

import java.io.IOException;
import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-04
 **/
public interface SiteCrawlerI {

    List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException;

}
