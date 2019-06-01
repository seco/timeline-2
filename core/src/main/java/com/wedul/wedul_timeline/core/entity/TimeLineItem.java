package com.wedul.wedul_timeline.core.entity;

import com.wedul.wedul_timeline.core.type.EnumSiteType;
import lombok.*;

import javax.persistence.*;

/**
 * timeline item entity
 *
 * @author wedul
 * @since 2019-05-31
 **/
@Getter
@Setter
@Builder
@Table
@AllArgsConstructor
@Entity(name = "timeline_item")
public class TimeLineItem extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long timelineId;

    @Enumerated(value = EnumType.ORDINAL)
    private EnumSiteType siteType;

    @Column
    private String siteName;

    @Column
    private String siteUrl;

    @Column
    private String imageUrl;

    @Column
    private String content;

}
