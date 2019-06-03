package com.wedul.wedul_timeline.core.entity;

import com.wedul.wedul_timeline.core.type.EnumSiteType;
import lombok.*;

import javax.persistence.*;

/**
 * timelineItem item entity
 *
 * @author wedul
 * @since 2019-05-31
 **/
@Getter
@Builder
@Table(name = "timeline_site")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "timeline_site")
public class TimeLineSite extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long siteId;

    @Enumerated(value = EnumType.ORDINAL)
    private EnumSiteType siteType;

    @Column
    private String siteName;

    @Column
    private String siteUrl;

}
