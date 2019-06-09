package com.wedul.wedul_timeline.batch.service.job;

import com.wedul.wedul_timeline.batch.service.job.dto.WoowahanDto;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-09
 **/
@Service
@Slf4j
public class WoowahanJobService extends JobCrawlService {

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) {
        RestTemplate restTemplate = new RestTemplate();
        List<WoowahanDto> ret = Arrays.asList(restTemplate.getForObject(timeLineSite.getSiteUrl(), WoowahanDto[].class));
        List<TimeLineItem> timeLineItems = new ArrayList<>();

        ret.forEach(woowahanDto -> {
            timeLineItems.add(TimeLineItem.builder().
                    landingUrl(timeLineSite.getSiteUrl())
                    .content(woowahanDto.getContents())
                    .title(woowahanDto.getJobTitle())
                    .build());
        });
        return timeLineItems;
    }
}
