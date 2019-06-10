package com.wedul.wedul_timeline.batch.job.timelineItem;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
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
public class TimeLineItemJpaItemWriter extends JpaItemWriter<List<TimeLineItem>> {

    private final EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void initialize() {
        setEntityManagerFactory(entityManagerFactory);
    }

    @Override
    public void write(List<? extends List<TimeLineItem>> items) {
        super.write(items);
    }
}
