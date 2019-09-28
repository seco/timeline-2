package com.wedul.wedul_timeline.batch.step.job.api;

import com.wedul.wedul_timeline.batch.step.job.dto.ElevenDto;
import com.wedul.wedul_timeline.core.config.error.NotFoundException;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 11번가 채용 서비스
 *
 * @author wedul
 * @since 2019-09-28
 **/
@Slf4j
@Service("ElevenStJobService")
public class ElevenStJobService extends ApiJobService {
    @Override
    public String logoUrl(String subUrl) {
        return "https://www.11stcorp.com/resources/img/bi.png";
    }

    public String contentClass() { return "sk-box-job-detail"; }

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) throws IOException {
        ElevenDto elevenDto = restTemplate().getForObject(timeLineSite.getSiteUrl(), ElevenDto.class);
        List<TimeLineItem> timeLineItems = new ArrayList<>();

        if (null == elevenDto) throw new NotFoundException("11번가 채용정보를 가져오는데 실패하였습니다.");

        elevenDto.getItems().forEach(data -> {
            try {
                if (data.getJobGroups().get(0).getJobGroupCodeName().equals("Engineering")) {
                    String detailPageSite = getDetailPageSite(data.getAnnouncementId());

                    Document document = Jsoup.connect(detailPageSite).get();
                    String content = document.getElementsByClass(contentClass()).get(0).html();

                    TimeLineItem timeLineItem = TimeLineItem.builder()
                        .sourceId(getSourceId(detailPageSite))
                        .title(data.getTitle())
                        .landingUrl(detailPageSite)
                        .timeLineSite(timeLineSite)
                        .logoUrl(logoUrl(StringUtils.EMPTY))
                        .content(content)
                        .publishedAt(data.getAnnouncementDate())
                        .build();

                    timeLineItems.add(timeLineItem);
                }
            } catch (Exception e) {
                log.error("11번가 채용 상세 페이지를 가져오는데 실패하였습니다.");
            }
        });

        return timeLineItems;
    }

    private String getDetailPageSite(long id) {
        return new StringBuilder("https://careers.11stcorp.com/jobs/detail/").append(id).toString();
    }
}
