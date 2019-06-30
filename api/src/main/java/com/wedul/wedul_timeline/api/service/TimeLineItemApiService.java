package com.wedul.wedul_timeline.api.service;

import com.wedul.wedul_timeline.core.config.error.NotFoundException;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.repository.TimeLineItemRepository;
import com.wedul.wedul_timeline.core.type.EnumSiteType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 아이템 관련 서비스
 *
 * @author wedul
 * @since 2019-05-30
 **/
@Service
@RequiredArgsConstructor
public class TimeLineItemApiService {

    private final TimeLineItemRepository timeLineItemRepository;

    /**
     * enum site type 가져오기.
     *
     * @param
     * @return
     */
    @Transactional
    public Page<TimeLineItem> timeLineItems(Pageable pageable) {
        Page<TimeLineItem> timeLineItems = timeLineItemRepository.findAll(pageable);
        if (timeLineItems == null) throw new NotFoundException();
        return timeLineItems;
    }

    /**
     * enum site type 가져오기.
     *
     * @param
     * @return
     */
    @Transactional
    public Page<TimeLineItem> timeLineItemsBySiteType(Pageable pageable, String type) {
        EnumSiteType enumSiteType = EnumSiteType.getSiteType(type);
        if (null == enumSiteType) throw new NotFoundException();
        Page<TimeLineItem> timeLineItems = timeLineItemRepository.findAllBySiteType(pageable, type);

        if (timeLineItems == null) throw new NotFoundException();
        return timeLineItems;
    }


}
