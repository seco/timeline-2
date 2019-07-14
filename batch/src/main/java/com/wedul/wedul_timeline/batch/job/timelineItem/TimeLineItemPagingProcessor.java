package com.wedul.wedul_timeline.batch.job.timelineItem;

import com.wedul.wedul_timeline.batch.service.kafka.KafkaService;
import com.wedul.wedul_timeline.batch.step.SiteCrawlerI;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.util.ObjectHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-03
 **/
@Component
@StepScope
@AllArgsConstructor
@Slf4j
public class TimeLineItemPagingProcessor implements ItemProcessor<TimeLineSite, List<TimeLineItem>> {

    private ApplicationContext applicationContext;
    private KafkaService kafkaService;

    @Override
    public List<TimeLineItem> process(TimeLineSite item) {
        try {
            SiteCrawlerI siteCrawlerI = (SiteCrawlerI) applicationContext.getBean(item.getSiteName() + "Service");

            List<TimeLineItem> timeLineItems = siteCrawlerI.crawl(item);

            timeLineItems.forEach(timeLineItem -> {
                try {
                    timeLineItem.setCopyTimeLineSite(item);
                    kafkaService.sendMessage(ObjectHelper.getInstance().writeValueAsString(timeLineItem));
                } catch (Exception e) {
                    log.info("카프카에 크롤링 데이터를 넣는데 실패하였습니다.", e);
                }
            });

            return timeLineItems;
        } catch (Exception e) {
            log.error("데이터를 가져오는데 실패하였습니다.", e);
        }

        return Collections.emptyList();
    }
}
