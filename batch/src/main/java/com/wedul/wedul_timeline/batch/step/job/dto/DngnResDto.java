package com.wedul.wedul_timeline.batch.step.job.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-23
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DngnResDto {

    private DngnResDetailDto results[];

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DngnResDetailDto {
        private String role;
        private DngnValueDto value;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DngnValueDto {
        private String id;
        private int version;
        private DngnTitle properties;
        private String[] content;
        private boolean alive;
        private long last_edited_time;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DngnTitle {
        private Object[] title;
    }

}
