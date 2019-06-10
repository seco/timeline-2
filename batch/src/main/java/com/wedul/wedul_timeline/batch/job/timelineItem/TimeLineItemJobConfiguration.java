package com.wedul.wedul_timeline.batch.job.timelineItem;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Time Job Configuration
 *
 * @author wedul
 * @since 2019-05-31
 **/
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "job.name", havingValue = TimeLineItemJobConfiguration.JOB_NAME)
public class TimeLineItemJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final TimeLineItemPagingProcessor payPagingProcessor;
    private final TimeLineItemJpaItemWriter timeLineItemJpaItemWriter;

    public final static String JOB_NAME = "timelineCrawlerJob";
    private final int chunkSize = 10;

    @Bean
    public Job timelineCrawlerJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(timelineCrawlStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step timelineCrawlStep(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return stepBuilderFactory.get("timelineCrawlStep")
                .<TimeLineSite, List<TimeLineItem>>chunk(chunkSize)
                .reader(timeLineSitePageReader())
                .processor(payPagingProcessor)
                .writer(timeLineItemJpaItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<TimeLineSite> timeLineSitePageReader() {
        return new JpaPagingItemReaderBuilder<TimeLineSite>()
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT p FROM timeline_site p")
                .pageSize(chunkSize)
                .name("timeLineSitePageReader")
                .build();
    }

}
