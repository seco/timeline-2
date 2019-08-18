package com.wedul.wedul_timeline.batch.step.job.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-08-18
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoupangResDto implements Serializable {

    private String filters;

    private boolean hasJob;

    private String results;

}
