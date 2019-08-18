package com.wedul.wedul_timeline.batch.step.job.api;

import com.wedul.wedul_timeline.batch.step.job.dto.DngnDto;
import com.wedul.wedul_timeline.batch.step.job.dto.DngnResDto;
import com.wedul.wedul_timeline.core.config.error.NotFoundException;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-23
 **/
@Service("DngnJobService")
@Slf4j
public class DngnJobService extends ApiJobService {

    @Override
    public List<TimeLineItem> crawl(TimeLineSite timeLineSite) {
        // 컨텐츠 전체 정보
        DngnResDto dngnResDto = getNotion(restTemplate(), timeLineSite.getSiteUrl());
        String title = String.valueOf(dngnResDto.getResults()[0].getValue().getProperties().getTitle()[0]).replaceAll("\\[|]", StringUtils.EMPTY).trim();
        DngnData dngnData = getData(restTemplate(), timeLineSite.getSiteUrl(), dngnResDto.getResults()[0].getValue().getContent());

        return Arrays.asList(TimeLineItem.builder()
                .title(title)
                .timeLineSite(timeLineSite)
                .landingUrl(landingUrl())
                .sourceId(getSourceId(landingUrl()))
                .logoUrl(logoUrl(StringUtils.EMPTY))
                .publishedAt(dngnData.getPublishedAt())
                .content(dngnData.getContent())
                .build());
    }

    /**
     * 당근 마켓 채용 노션 기본 정보
     *
     * @param restTemplate
     * @param uri
     * @return
     */
    private DngnResDto getNotion(RestTemplate restTemplate, String uri) {
        DngnDto dngnReqDto = new DngnDto();
        dngnReqDto.setRequests(new DngnDto.DngnReqDto[]{DngnDto.DngnReqDto.builder().table("block").id("2c789a2c-7b1a-4cfc-a40b-11afba678315").build()});

        DngnResDto result = restTemplate.postForObject(uri, dngnReqDto, DngnResDto.class);
        if (null == result) throw new NotFoundException("당근마켓 정보를 가져오는데 실패하였습니다.");
        return result;
    }

    /**
     * 컨텐츠 내용 가져오기
     *
     * @param contentIds
     * @return
     */
    private DngnData getData(RestTemplate restTemplate, String uri, String[] contentIds) {
        StringBuilder sb = new StringBuilder();
        DngnData dngnData = new DngnData();

        Arrays.stream(contentIds).forEach(contentId -> {
            DngnDto dngnReqDto = new DngnDto();
            dngnReqDto.setRequests(new DngnDto.DngnReqDto[]{DngnDto.DngnReqDto.builder().table("block").id(contentId).build()});

            DngnResDto result = restTemplate.postForObject(uri, dngnReqDto, DngnResDto.class);

            if (result.getResults()[0].getValue().getLast_edited_time() > dngnData.getPublishedAt()) {
                dngnData.setPublishedAt(result.getResults()[0].getValue().getLast_edited_time());
            }

            if (null != result.getResults()[0].getValue().getProperties()) {
                sb.append("<p>");
                sb.append(String.valueOf(result.getResults()[0].getValue().getProperties().getTitle()[0]).replaceAll("\\[|]", StringUtils.EMPTY).trim());
                sb.append("</p>");
            }
        });

        dngnData.setContent(sb.toString());
        return dngnData;
    }

    @Override
    public String logoUrl(String subUrl) {
        return "https://d3qlrgda07sb6k.cloudfront.net/assets/home/base/header/logo-basic-00b7e471b721ce9db8b0758c05a84684413c8aef1ad54caa0f3fcbe7328c947f.svg";
    }

    @Override
    public String landingUrl() {
        return "https://www.notion.so/2c789a2c7b1a4cfca40b11afba678315";
    }

    @Data
    private static class DngnData {
        private String content;
        private long publishedAt;
    }

}
