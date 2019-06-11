package com.wedul.wedul_timeline.core.entity;

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
@Table(name = "timeline_item")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity(name = "timeline_item")
public class TimeLineItem extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private TimeLineSite timeLineSite;

    @Column
    private String sourceId;

    @Column
    private String title;

    @Column
    private String landingUrl;

    @Column
    private String content;

}
