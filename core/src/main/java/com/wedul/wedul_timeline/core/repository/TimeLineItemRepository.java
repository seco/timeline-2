package com.wedul.wedul_timeline.core.repository;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-01
 **/
@Repository
public interface TimeLineItemRepository extends CrudRepository<TimeLineItem, Long> {
}
