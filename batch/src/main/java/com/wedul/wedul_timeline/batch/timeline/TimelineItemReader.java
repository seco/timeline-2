package com.wedul.wedul_timeline.batch.timeline;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-31
 **/
@Component
@StepScope
public class TimelineItemReader implements ItemReader<TimeLineItem> {

    @Override
    public TimeLineItem read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
