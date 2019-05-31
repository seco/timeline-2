package com.wedul.wedul_timeline.batch.timeline;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Time Job Configuration
 *
 * @author wedul
 * @since 2019-05-31
 **/
@Configuration
@RequiredArgsConstructor
public class TimelineJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TimelineItemReader timelineItemReader;
    private final TimelineItemWriter timelineItemWriter;

    @Bean
    public Job timelineCrawlerJob() {
        return jobBuilderFactory.get("timelineCrawlerJob")
                .incrementer(new RunIdIncrementer())
                .start(timelineCrawlStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step timelineCrawlStep(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return stepBuilderFactory.get("timelineCrawlStep")
                .<TimeLineItem, TimeLineItem>chunk(10)
                .reader(timelineItemReader)
                .writer(timelineItemWriter)
                .build();
    }

}
