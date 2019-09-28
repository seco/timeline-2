package com.wedul.wedul_timeline.batch.step.job.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 11번가 Dto
 *
 * @author wedul
 * @since 2019-09-28
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElevenDto {

    private List<ElevenItemDto> items;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ElevenItemDto {

        private long announcementId;

        private List<ElevenItemGroupDto> jobGroups;

        private long endDate;

        private long beginDate;

        private String title;

        private long announcementDate;

        private String lang;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ElevenItemGroupDto {

        private String jobGroupCodeName;

    }

}


