package com.wedul.wedul_timeline.api.dto;

import com.wedul.wedul_timeline.core.type.EnumSiteType;
import lombok.Getter;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-29
 **/
@Getter
public class ItemReqDto {
    private EnumSiteType enumSiteType;
    private int cursor;
    private int size;
}