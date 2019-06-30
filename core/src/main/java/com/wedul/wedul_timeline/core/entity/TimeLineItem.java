package com.wedul.wedul_timeline.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

/**
 * timelineItem item entity
 *
 * @author wedul
 * @since 2019-05-31
 **/
@Data
@Builder
@Table(name = "timeline_item")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity(name = "timeline_item")
public class TimeLineItem extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    @JsonBackReference
    private TimeLineSite timeLineSite;

    @Transient
    private TimeLineSite copyTimeLineSite;

    @Column
    private String sourceId;

    @Column
    private String title;

    @Column
    private String landingUrl;

    @Column
    private String logoUrl;

    @Column
    private String content;

    public void copy(TimeLineItem timeLineItem) {
        this.setSourceId(timeLineItem.getSourceId());
        this.setTitle(timeLineItem.getTitle());
        this.setLandingUrl(timeLineItem.getLandingUrl());
        this.setLogoUrl(timeLineItem.getLogoUrl());
        this.setContent(timeLineItem.getContent());
        this.setUpdateAt(System.currentTimeMillis());
    }

}
