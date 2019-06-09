package com.wedul.wedul_timeline.batch.service.job;

import com.wedul.wedul_timeline.batch.service.job.dto.WoowahanDto;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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
public class WoowahanService extends JobCrawlService {


    @Override
    String title() {
        return null;
    }

    @Override
    String content() {
        return null;
    }

    @Override
    String mainImageUrl() {
        return null;
    }

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        String ret = restTemplate.getForObject(timeLineSite.getSiteUrl(), String.class);

        System.out.println(ret);
        return null;
    }
}
