package com.wedul.wedul_timeline.batch.step.job.page;

import com.wedul.wedul_timeline.batch.step.job.JobCrawlService;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-08-18
 **/
@Service("CoupangJobService")
public class CoupangJobService extends JobCrawlService {


    @Override
    public String logoUrl(String subUrl) {
        return null;
    }

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        return null;
    }
}
