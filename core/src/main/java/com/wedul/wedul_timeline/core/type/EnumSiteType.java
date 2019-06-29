package com.wedul.wedul_timeline.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * site type
 *
 * @author wedul
 * @since 2019-05-31
 **/
@Getter
@AllArgsConstructor
public enum EnumSiteType {

    JOB(0),
    TECH(1),
    APT(2);

    private int number;
}
