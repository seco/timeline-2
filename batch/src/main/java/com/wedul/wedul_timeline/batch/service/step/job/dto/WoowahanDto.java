package com.wedul.wedul_timeline.batch.service.step.job.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-09
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WoowahanDto {

    @JsonProperty("BusinessName")
    private String businessName;

    @JsonProperty("Contents")
    private String contents;

    @JsonProperty("JobTitle")
    private String jobTitle;

    @JsonProperty("EDate")
    private String eDate;
}
