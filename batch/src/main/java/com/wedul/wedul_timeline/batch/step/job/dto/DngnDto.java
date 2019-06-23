package com.wedul.wedul_timeline.batch.step.job.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 당근마켓 notion 페이지 정보
 *
 * @author wedul
 * @since 2019-06-23
 **/
@Data
public class DngnDto implements Serializable {

    @JsonProperty("requests")
    private DngnReqDto[] requests;

    @Builder
    @Data
    public static class DngnReqDto implements Serializable {
        @JsonProperty("table")
        private String table;

        @JsonProperty("id")
        private String id;
    }
}


