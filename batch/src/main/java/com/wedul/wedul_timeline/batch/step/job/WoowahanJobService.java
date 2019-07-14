package com.wedul.wedul_timeline.batch.step.job;

import com.wedul.wedul_timeline.batch.step.job.dto.WoowahanDto;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.util.HashUtil;
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
@Service("WoowahanJobService")
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
                    .timeLineSite(timeLineSite)
                    .sourceId(getSourceId(woowahanDto.getJobTitle()))
                    .logoUrl("https://www.woowahan.com/img/pc/common-logo.png")
                    .content(woowahanDto.getContents())
                    .title(woowahanDto.getJobTitle())
                    .build());
        });
        return timeLineItems;
    }
}