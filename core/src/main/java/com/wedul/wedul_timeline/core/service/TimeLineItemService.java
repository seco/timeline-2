package com.wedul.wedul_timeline.core.service;

import com.wedul.wedul_timeline.core.config.Constant;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.repository.TimeLineItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-16
 **/
@Service
@RequiredArgsConstructor
public class TimeLineItemService {

    private final TimeLineItemRepository timeLineItemRepository;

    @Transactional
    @CacheEvict(cacheNames = Constant.TIMELINE_ITEM_CACHE_KEY, key = "#timeLineItem.sourceId")
    public void setTimeLineItem(TimeLineItem timeLineItem) {
        timeLineItemRepository.save(timeLineItem);
    }

    @Transactional
    @Cacheable(cacheNames = Constant.TIMELINE_ITEM_CACHE_KEY, key = "#sourceId", sync = true)
    public TimeLineItem getTimeLineItem(String sourceId) {
        TimeLineItem timeLineItem = timeLineItemRepository.findBySourceId(sourceId);
        if (null != timeLineItem) {
            timeLineItem.getTimeLineSite();
        }

        return timeLineItem;
    }

}
