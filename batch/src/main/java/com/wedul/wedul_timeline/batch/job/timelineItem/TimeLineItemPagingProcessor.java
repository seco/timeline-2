package com.wedul.wedul_timeline.batch.job.timelineItem;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-03
 **/
@Component
@StepScope
public class TimeLineItemPagingProcessor implements ItemProcessor<TimeLineSite, TimeLineItem> {

    @Override
    public TimeLineItem process(TimeLineSite item) {
        return TimeLineItem.builder().build();
    }
}
