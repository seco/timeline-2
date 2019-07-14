package com.wedul.wedul_timeline.api.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-29
 **/
@Data
public class ItemReqDto {
    private String searchQuery;
    private String searchDate; // '2019-04-24'

    public boolean hasCondition() {
        return StringUtils.isNotBlank(searchQuery) || StringUtils.isNotBlank(searchDate);
    }
}