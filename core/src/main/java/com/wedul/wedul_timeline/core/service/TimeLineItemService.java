package com.wedul.wedul_timeline.core.service;

import com.wedul.wedul_timeline.core.config.repository.RedisRepositoryConfig;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.repository.TimeLineItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

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

    @CacheEvict(cacheNames = RedisRepositoryConfig.TIME_LINE_ITME, key = "#timeLineItem.sourceId")
    public void setTimeLineItem(TimeLineItem timeLineItem) {
        timeLineItemRepository.save(timeLineItem);
    }

}
