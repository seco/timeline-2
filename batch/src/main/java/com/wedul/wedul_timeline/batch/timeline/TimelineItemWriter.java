package com.wedul.wedul_timeline.batch.timeline;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-31
 **/
@Component
@StepScope
public class TimelineItemWriter implements ItemWriter<TimeLineItem> {

    @Override
    public void write(List<? extends TimeLineItem> items) throws Exception {

    }
}
