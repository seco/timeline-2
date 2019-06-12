package com.wedul.wedul_timeline.batch.job.timelineItem;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-03
 **/
@Component
@StepScope
@RequiredArgsConstructor
public class TimeLineItemItemWriter implements ItemWriter<List<TimeLineItem>> {

    @Override
    public void write(List<? extends List<TimeLineItem>> items) {
    }
}
