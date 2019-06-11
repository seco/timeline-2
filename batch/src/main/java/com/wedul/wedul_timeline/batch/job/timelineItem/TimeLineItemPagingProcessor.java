package com.wedul.wedul_timeline.batch.job.timelineItem;

import com.wedul.wedul_timeline.batch.service.SiteCrawlerI;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-03
 **/
@Component
@StepScope
public class TimeLineItemPagingProcessor implements ItemProcessor<TimeLineSite, List<TimeLineItem>> {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public List<TimeLineItem> process(TimeLineSite item) throws IOException {
        SiteCrawlerI siteCrawlerI = (SiteCrawlerI) applicationContext.getBean(item.getSiteName() + "Service");
        return siteCrawlerI.crawl(item);
    }
}
