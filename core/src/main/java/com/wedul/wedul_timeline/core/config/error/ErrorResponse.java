package com.wedul.wedul_timeline.core.config.error;

import lombok.Builder;
import lombok.Data;

/**
 * 에러 응답 response
 *
 * @author wedul
 * @since 2019-05-26
 **/
@Data
@Builder
public class ErrorResponse {

    private int errCode;
    private String message;
}
