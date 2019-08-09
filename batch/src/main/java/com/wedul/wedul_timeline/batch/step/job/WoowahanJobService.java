package com.wedul.wedul_timeline.batch.step.job;

import com.wedul.wedul_timeline.batch.step.job.dto.WoowahanDto;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
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
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite)  {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        headers.set("Cookie", "rbzid=TjacRvM7qfzu9j5QKlRBJtKVO+jGOeue8hRFDh4fTBXak5MvEGbmnMER6xV0yRXya/UNw6RiLGSPHCOyFOogCIORVCwuRAwKhhuUJ+jPvknhMYVfIScPT4mScLuGoLoaxAnB9zHh0hD678x90E1QF3KOBMGU6XaKDhzgmBWdvhrYd6vBrOfRg5i9bv/QrosPOmHPby2+LPRo+sQ6dwmvOo+N+nDSs3imAlab7A9TdLxKsAGhGmfNYEzVNpXm9LvAXrmPzJbcFnbBRtzDOt9wq6jD69VYqJ2+XRv496l1/pSAf2hqhpxYXUq4TujLwjyj; rbzsessionid=30f52617d70836f750fa4aef5a550907");

        HttpEntity entity = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WoowahanDto[]> ret = restTemplate.exchange(timeLineSite.getSiteUrl(), HttpMethod.GET, entity, WoowahanDto[].class);
        List<TimeLineItem> timeLineItems = new ArrayList<>();
        Arrays.stream(ret.getBody()).forEach(woowahanDto -> {
            try {
                timeLineItems.add(TimeLineItem.builder().
                        landingUrl(timeLineSite.getSiteUrl())
                        .timeLineSite(timeLineSite)
                        .sourceId(getSourceId(woowahanDto.getJobTitle()))
                        .logoUrl("https://www.woowahan.com/img/pc/common-logo.png")
                        .content(woowahanDto.getContents())
                        .title(woowahanDto.getJobTitle())
                        .publishedAt(DateUtil.startUnixTimeStamp(woowahanDto.getSDate().split("T")[0]))
                        .build());
            } catch (ParseException e) {
                log.error("date parsing error", e);
            }
        });
        return timeLineItems;
    }
}
